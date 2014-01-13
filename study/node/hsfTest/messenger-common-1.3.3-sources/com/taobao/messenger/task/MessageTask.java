package com.taobao.messenger.task;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.MessageSenderService;
import com.taobao.messenger.service.common.Channel;

/**
 * 基本信息发送任务数据单元。<br>
 * 包括推送目标{@link #addTarget}、消息类型{@link #setMessageTypeId}、任务计划执行时间{@link #setScheduleTime}、是否需要将任务持久
 * {@link #setPersistence}等任务必要信息<br>
 * <li>初始化失败将抛出 {@link MessageTaskInitException}<br> <li>参数填入完成后，可通过{@code isValid()}校验是否已经填入必要参数，<br>
 * 任务生成成功后，通过{@link MessageSenderService#send}或{@link MessageSenderService#submit}接口发送任务<br>
 * 
 * @author tianhu
 * 
 */
public interface MessageTask extends Serializable
{

    public static final String TARGET_SPLIT = ",";
    public static final String KEYVALUE_SPLIT = "=";
    public static final int MAX_TARGETS_LENGTH = 800;

    /**
     * 判断是否设置了必要的参数
     * 
     * @return true-所有必要参数已经设置
     */
    public boolean isValid();

    /**
     * @return 发送者ID
     */
    public String getSourceId();

    /**
     * 设置发送来源，默认为0-将使用系统默认发送源信息
     * @param sourceId 发送者ID, 可选
     * @throws MessageTaskInitException
     */
    public void setSourceId(String sourceId) throws MessageTaskInitException;

    /**
     * 获取所有发送目标用户
     * 
     * @return 接收消息的user列表，以特殊字符隔开-如逗号","或分号";"
     */
    public String getTargets();

    /**
     * 添加目标用户，一次只能添加一个用户。
     * 
     * @param target 对应用户标示
     * @return 当前已经添加用户数量
     * @throws MessageTaskInitException target不符合对应实现约束、prefix不正确均会抛出异常、已加入人数超过允许发送上限
     */
    public int addTarget(String target) throws MessageTaskInitException;

    /**
     * 设置用于大批量人群发送，用于表示人群来源信息的标签
     * @param tag 人群标签
     * @param targetsNum 目标人群数量
     * @throws MessageTaskInitException tag不符合对应实现约束、目标人群数=<0时抛出异常
     */
    public void setTag(String tag, int targetsNum) throws MessageTaskInitException;

    /**
     * 清除所有目标对象(For test)
     */
    public void clearTargets();

    /**
     * @return 发送用户前缀：ID、Mail、Phone或Tag
     */
    public String getUserInfoPrefix();

    /**
     * 获取目标用户数量 以target方式传递用户的targetsNum就为target内的数目 以taggedTarget方式传递用户的targetsNum就为被标记的用户群数量
     * 
     * @return 目标用户数
     */
    public int getTargetsNum();

    /**
     * 获取用于模板渲染的相关参数以及参数值
     * 
     * @return 用于模板渲染的Key-Value键值对
     */
    public Map<String, Object> getContext();

    /**
     * 重置所有用于消息模板渲染的相关参数以及参数值<br>
     * 消息的标题和正文内容所需的key-value对均可通过此方法指定<br>
     * 
     * @param context 用于模板渲染的Key-Value键值对，目前仅支持{@code Map<String, String>}
     * @throws MessageTaskInitException context为null或context中包括null的key-value时抛出
     */
    public void setContext(Map<String, Object> context) throws MessageTaskInitException;

    /**
     * 添加用于消息模板渲染的相关参数以及参数值。<br>
     * 消息的标题和正文内容所需的key-value对均可通过此方法添加，<br>
     * 已经存在key将被覆盖
     * 
     * @param key 渲染变量key
     * @param value 渲染变量value，目前仅支持{@code String} 类型
     * @throws MessageTaskInitException key-value为null
     */
    public void addContext(String key, Object value) throws MessageTaskInitException;

    /**
     * 获取任务ID（备用，目前无实际意义）
     * 
     * @return 任务ID
     */
    public String getTaskId();

    /**
     * 设置任务ID（备用，目前无实际意义）
     * 
     * @param taskId
     * @throws MessageTaskInitException
     */
    public void setTaskId(String taskId) throws MessageTaskInitException;

    /**
     * 获取计划发送时间
     * 
     * @return 定时发送的时间
     */
    public Date getScheduleTime();

    /**
     * 设置任务计划发送时间，延时任务将存放至指定时间点进行发送。<br>
     * 不支持延时时间大于30天的任务发送
     * 
     * @param scheduleTime 计划发送时间
     * @throws MessageTaskInitException 计划执行时间早于当前系统时间将抛出异常
     */
    public void setScheduleTime(Date scheduleTime) throws MessageTaskInitException;

    /**
     * 获取任务的过期时间，过期的任务将自动放弃执行
     * 
     * @return 任务的过期时间
     */
    public Date getScheduleDeadline();

    /**
     * (optional)<br>
     * 设置任务的过期时间，默认为计划执行时间+1年<br>
     * 过期仍未能执行的任务将自动放弃执行
     * 
     * @param deadline 消息过期的时间
     * @throws MessageTaskInitException 过期时间早于计划执行时间将抛出异常
     */
    public void setScheduleDeadline(Date deadline) throws MessageTaskInitException;

    /**
     * 获取是否需要依据用户个性化信息填充最终发送内容
     * 
     * @return true - 需要
     */
    public boolean isNeedDiversity();

    /**
     * (optional)<br>
     * 指定任务是否需要加载用户如NICK等个性化信息。<br>
     * 默认为不加载 (暂无实际作用)
     * 
     * @param needDiversity true-需要加载用户信息个性化信息
     */
    public void setNeedDiversity(boolean needDiversity);

    /**
     * 判定任务时序需要持久化，以备发送记录查询
     * @return 是否需要持久化
     */
    public boolean isPersistence();

    /**
     * (optional)<br>
     * 设置任务是否需要持久化，以备发送记录查询<br>
     * （可选，默认未非持久） 若MessageType中设置为不需要持久，此处的设置无效
     * 
     * @param persistence true-需要
     */
    public void setPersistence(boolean persistence);

    /**
     * 获取任务所归属的消息类型
     * 
     * @return 任务类型ID
     */
    public int getMessageTypeId();

    /**
     * 设置任务所归属的消息类型<br>
     * （消息类型MessageType与优先级、通道、是否允许持久、是否需要疲劳度过滤等信息关联）
     * 
     * @param messageTypeId 任务类型ID
     * @throws MessageTaskInitException id不符实现类约定将抛出异常
     */
    public void setMessageTypeId(int messageTypeId) throws MessageTaskInitException;

    /**
     * 获取消息发送需使用的所有通道
     * @see Channel
     * @return 任务发送通道转变为十进制后的和
     */
    public int getChannels();

    /**
     * 添加消息发送需使用通道-{@link Channel} 以及所需使用的对应模板
     * 
     * @see Channel
     * @param channel 任务发送通道，由实现在内部转换为内部存储格式
     * @param templetId 任务发送通道对应的模板Id
     * @throws MessageTaskInitException channel重复设置、templetId不符实现类约定时将抛出异常
     */
    public void addChannel(Channel channel, int templetId) throws MessageTaskInitException;

    /**
     * 获取通道、对应模板字符串
     * @return 通道和模板ID对应关系字符串，形式如：8=123456;2=123457
     */
    public String getChannelTempletId();

    /**
     * 清除所有通道属性(For test)
     */
    public void clearChannels();
    
	/**
	 * 增加订阅时的业务类型，设置订阅消息的业务类型
	 * @param businessTpye
	 * @return
	 */
	public void setBusinessTpye(int businessTpye);

	/**
	 * 得到消息的订阅业务类型
	 * @return
	 */
	public int getBusinessTpye();


}
