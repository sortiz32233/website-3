package com.taobao.messenger.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.common.Channel;
import com.taobao.messenger.service.common.UserInfoPrefix;
import com.taobao.messenger.task.MessageTask;

/**
 * 默认任务实现类。<br>
 * 发送目标设置：可以通过以下两种方式添加
 * <ul>
 * <li>以用户数字id方式添加target:{@link #addTarget(String)}，同一任务所添加用户数不多于40个</li>
 * <li>以tag方式添加批量用户: {@link #setTag(String, int)} ，可支持大于40的用户人群传送，需先将用户目标信息上传至target_List数据库</li>
 * </ul>
 * 相关参数设定：
 * <ul>
 * <li> {@link #addChannel(Channel, int)}添加所需发送的通道以及对应选用的模板id</li>
 * <li> {@link #setMessageTypeId(int)} 设置任务所归属的消息类型，确定任务优先级、是否需要持久、疲劳度过滤等信息</li>
 * <li> {@link #setContext(Map)} 设置消息所使用的模板中的实际key-value值</li>
 * <li> {@link #setScheduleTime(Date)} 设置任务的执行时间</li>
 * </ul>
 * (发送任务前需先通过管理平台设定MessageType、MessageTemplet并获取对应id) <br>
 * 设定完成后可通过 {@link #isValid()} 检查是否已经设定完必要参数。
 * 
 * @author tianhu
 * @see MessageTask
 */
public class DefaultMessageTask implements MessageTask
{
    private static final int UID_UPPER_LIMIT = 40;
    private static final long ONE_HOUR = 3600000;
    private static final long ONE_DAY = 24 * ONE_HOUR;
    private static final long ONE_YEAR = 365 * ONE_DAY;

    private static final long serialVersionUID = -3346970538131677076L;

    /**
     * 发送者ID, 可选
     */
    private String sourceId = "System";

    /**
     * 接收消息的目标标记字段：userID、Mail地址、手机号码或Tag人群标签
     */
    private String userInfoPrefix = UserInfoPrefix.UID.getValue();
    /**
     * 具体target对象拼接成的字符串，userInfoPrefix标记的具体值，多个之间由TARGET_SPLIT分割
     */
    protected String targets = "";

    /**
     * 记录target，用于快速进行重复target添加判定，不进入网络传递
     */
    //允许重复的target
    transient protected List<String> targetList = null;

    /**
     * 接收消息的user计数
     */
    protected int targetsNum = 0;

    /**
     * 模板渲染所需使用的相关变量取值key-Value对
     */
    private final Map<String, Object> context = new HashMap<String, Object>();

    /**
     * 任务id
     */
    private String taskId;
    /**
     * 定时发送的时间,默认为当前系统时间
     */
    private final Date scheduleTime = new Date(System.currentTimeMillis());
    /**
     * 消息超时的时间，默认为当前系统时间+1年
     */
    private final Date scheduleDeadline = new Date(System.currentTimeMillis() + ONE_YEAR);
    /**
     * 是否需要依据用户信息个性化标示
     */
    private boolean needDiversity = false;
    /**
     * 是否需要持久化
     */
    private boolean persistence = false;
    
	/**
     * 消息携带的业务类型，根据这个业务类型可以查到订阅的通道
     */
	private int businessTpye=0;
    /**
     * 任务类型(规则)ID
     */
    private int messageTypeId = -1;

    /**
     * Channel为Auto时选择MessageType中设定的通道， 其它时取MessageTask中的Channel 与MessageType中的Channel中的交集
     */
    private int channel = 0x00;
    private String templetIdList = "";

    /**
     * 默认构造函数
     */
    public DefaultMessageTask()
    {
    }

    /**
     * @param userInfo 用户信息前置标示：ID、Mail或手机号码
     */
    protected DefaultMessageTask(final UserInfoPrefix userInfo)
    {
        userInfoPrefix = userInfo.getValue();
    }

    public String getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(final String sourceId) throws MessageTaskInitException
    {
        if (null == sourceId)
        {
            throw new MessageTaskInitException("sourceId can't be null");
        }
        this.sourceId = sourceId;
    }

    public String getTargets()
    {
        return targets;
    }

    /**
     * 添加用户userId ,Task内部拼接为自定义格式的字符串
     * 
     * @param uid 对应UIC中的数字id，Long型，id > 0
     * @return 当前已经添加用户数量
     * @throws MessageTaskInitException uid无法转换为Long型、uid <= 0、prefix不正确、目标人群数超出限制均会抛出异常
     */
    public int addTarget(final String uid) throws MessageTaskInitException
    {
        isTargetValid(uid);
        if ((targets.length() + TARGET_SPLIT.length() + uid.length()) >= MAX_TARGETS_LENGTH)
        {
            throw new MessageTaskInitException("Targets numer has reached to upper limit");
        }
        if (targetsNum == 0)
        {
            targetList = new ArrayList<String>();
            targetList.add(uid);
            targets = uid;
        } else
        {
            if (targetList.add(uid))
            {
                targets += TARGET_SPLIT + uid;
            } else
            {
                throw new MessageTaskInitException(userInfoPrefix + " " + uid + " already exist.");
            }
        }
        targetsNum = targetList.size();

        return targetsNum;
    }

    /**
     * 判定传入的target是否合法
     * 
     * @param uid
     * @return TODO
     * @throws MessageTaskInitException
     */
    protected boolean isTargetValid(final String uid) throws MessageTaskInitException
    {
        if (!userInfoPrefix.equals(UserInfoPrefix.UID.getValue()))
        {
            throw new MessageTaskInitException("Task tag expect UID,but is " + userInfoPrefix);
        }

        try
        {
            final Long id = Long.parseLong(uid);
            if (id <= 0)
            {
                throw new MessageTaskInitException("Target id can't be nagetive,but is " + id);
            }
        } catch (final NumberFormatException e)
        {
            throw new MessageTaskInitException("Target expect long type UID,but is " + uid);
        }

        return true;
    }

    /**
     * 设置批量发送人群标签
     * 
     * @param tag 人群标签，带版本号，用"="分割。形式tag=version ，如11385=1
     * @param targetsNum 目标人群数量
     * @throws MessageTaskInitException tag不符合对应实现约束、目标人群数<=0时抛出异常
     */
    public void setTag(final String tag, final int targetsNum) throws MessageTaskInitException
    {
        if (this.targetsNum != 0)
        {
            throw new MessageTaskInitException("tag already be set to " + userInfoPrefix + ":" + targets);
        }
        if (null == tag)
        {
            throw new MessageTaskInitException("set tag is null");
        }
        if (targetsNum <= 0)
        {
            throw new MessageTaskInitException("targetsNum can't set negative or zero");
        }
        userInfoPrefix = UserInfoPrefix.TAG.getValue();
        targets = tag;
        this.targetsNum = targetsNum;
    }

    public void clearTargets()
    {
        targets = "";
        targetsNum = 0;
        userInfoPrefix = UserInfoPrefix.UID.toString();
    }

    public String getUserInfoPrefix()
    {
        return userInfoPrefix;
    }

    public Map<String, Object> getContext()
    {
        return context;
    }

    public void setContext(final Map<String, Object> context) throws MessageTaskInitException
    {
        if (null == context)
        {
            throw new MessageTaskInitException("input context is null.");
        }
        this.context.clear();
        for (final Entry<String, Object> c : context.entrySet())
        {
            if (null == c.getKey())
            {
                throw new MessageTaskInitException("context map include null key.");
            }
            if (null == c.getValue())
            {
                throw new MessageTaskInitException("context value is null with key=" + c.getKey());
            }
            this.context.put(c.getKey(), c.getValue());
        }
    }

    public void addContext(final String key, final Object value) throws MessageTaskInitException
    {
        if (null == key)
        {
            throw new MessageTaskInitException("input key is null.");
        }
        if (null == value)
        {
            throw new MessageTaskInitException("input value is null.");
        }
        context.put(key, value);

    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(final String taskId)
    {
        this.taskId = taskId;
    }

    public Date getScheduleTime()
    {
        return scheduleTime;
    }

    /**
     * 设置定时发送时间，过期时间Deadline默认为此发送时间后一年
     * 
     * @param scheduleTime 定时发送的时间
     * @throws MessageTaskInitException 计划执行日期早于当前系统日期将抛出异常
     */
    @SuppressWarnings("deprecation")
    public void setScheduleTime(final Date scheduleTime) throws MessageTaskInitException
    {
        if (null == scheduleTime)
        {
            throw new MessageTaskInitException("schedule time is null");
        }
        final Date currentTime = new Date(System.currentTimeMillis());
        if (scheduleTime.getDate() < currentTime.getDate())
        {
            if (scheduleTime.getMonth() <= currentTime.getMonth())
            {
            	//排除跨年的可能
            	if(scheduleTime.getYear()<=currentTime.getYear())
            		
                throw new MessageTaskInitException("schedule date:" + scheduleTime
                        + " can't be earlier than current date:" + currentTime);
            }
        }
        if (scheduleTime.after(scheduleDeadline))
        {
            scheduleDeadline.setTime(scheduleTime.getTime() + ONE_YEAR);
        }
        this.scheduleTime.setTime(scheduleTime.getTime());

    }

    public Date getScheduleDeadline()
    {
        return scheduleDeadline;
    }

    public void setScheduleDeadline(final Date deadline) throws MessageTaskInitException
    {
        if (null == deadline)
        {
            throw new MessageTaskInitException("deadLine time is null");
        }
        if (deadline.before(scheduleTime))
        {
            throw new MessageTaskInitException("deadLine time:" + deadline + "is before scheduleTime:" + scheduleTime);
        }
        scheduleDeadline.setTime(deadline.getTime());
    }

    public boolean isNeedDiversity()
    {
        return needDiversity;
    }

    public void setNeedDiversity(final boolean needDiversity)
    {
        this.needDiversity = needDiversity;
    }

    public boolean isPersistence()
    {
        return persistence;
    }

    public void setPersistence(final boolean persistence)
    {
        this.persistence = persistence;
    }

    public int getMessageTypeId()
    {
        return messageTypeId;
    }

    public void setMessageTypeId(final int messageTypeId) throws MessageTaskInitException
    {
        if (messageTypeId < 0)
        {
            throw new MessageTaskInitException("messageTypeId can't be negative");
        }
        this.messageTypeId = messageTypeId;
    }

    public int getChannels()
    {
        return channel;
    }

    public void addChannel(final Channel channel, final int templetId) throws MessageTaskInitException
    {
        /**
         * yundong 应该先校验有效性，避免重复调用相同的内容，
         */
        if ((this.channel & channel.getValue()) > 0)
        {
            throw new MessageTaskInitException("channel already be set");
        }
        if (channel.getValue() == 0)
        {
            throw new MessageTaskInitException("channel can not be zero !");
        }
        if (templetId < 0)
        {
            throw new MessageTaskInitException("message templet Id can't be negative");
        }

        this.channel = this.channel | channel.getValue();

        if (templetIdList.length() == 0)
        {
            templetIdList = channel.getValue() + KEYVALUE_SPLIT + templetId;
        } else
        {
            templetIdList = templetIdList + TARGET_SPLIT + channel.getValue() + KEYVALUE_SPLIT + templetId;
        }
    }

    public String getChannelTempletId()
    {
        return templetIdList;
    }

    public void clearChannels()
    {
        channel = 0x00;
        templetIdList = "";
    }

    public int getTargetsNum()
    {
        return targetsNum;
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.messenger.task.MessageTask#getBusinessTpye()
	 */
	public int getBusinessTpye() {
		// TODO Auto-generated method stub
		return businessTpye;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.messenger.task.MessageTask#setBusinessTpye(int)
	 */
	public void setBusinessTpye(int businessTpye) {
	this.businessTpye=businessTpye;

	}

    public boolean isValid()
    {
        if (targets == null)
        {
            return false;
        }
        if (targetsNum <= 0)
        {
            return false;
        }
        if (messageTypeId < 0)
        {
            return false;
        }
        if (channel == 0)
        {
            return false;
        }
        return true;
    }

}
