/**
 * 
 */
package com.taobao.messenger.service.notify;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.taobao.messenger.service.common.MessageConstants;

/**
 * 消息发送目标,包括会员名称nick和id和接收消息的地址address, 可能是email地址, 也可能是旺旺nick
 * 消息中心前的消息模块旧版本兼容实现，逐步废弃
 * @author afei
 * @version 2008-1-10
 */
@Deprecated
public class MessageNotifiee implements Serializable {
    private static final long serialVersionUID = -4447098172073301877L;

    /**
     * 指定通过旺旺消息发送常量
     * */
    public static final String wangwang="arkWangwang" ;
    /**
     * 指定通过邮件发送常量
     * */
    public static final String mail="mail" ;
    /**
     * 指定通过站内信发送常量
     * */
    public static final String pmsg="pmsg" ;

    /**
     * 指定通过短信发送产量
     * */
    public static final String sms = "sms" ;

    /**
     * 会员名称, 可选
     */
    /*private String userNick;*/



    /**
     * 接收消息的地址address, 可能是email地址, 也可能是旺旺nick, 也可能是站内信会员id  必须
     * 可以用逗号","和分号";"指定发送多个地址
     */
    private String toAddress;


    /**
     * 接收消息的地址对应的用户,只有在发送站内信时需要，而且必须，其他不需要
     * 可以用逗号","和分号";"指定发送多个地址
     */
    private String toAddressNicks ;
    /*private String toAddressIds;*/


    /**
     * 消息发送模板id
     * */
    private String messageId;

    /**
     * 将MessageNotifiee的某些项放入TempletContext
     * 在调用setXXX()时同时放入context中
     */
    private Map<String, String> context = new HashMap<String, String>();

    /**
     * 会员ID, 可选
     */
    private String userId;

    /**
     * 订阅消息类型
     * **/
    private Integer msgType;

    /**
     * email地址
     * **/
    /*  private String email ;*/

    /*****************
     * 业务方法开始
     *****************/



    @Override
    public String toString() {
        return messageId + "-" + toAddress;
    }

    /**
     * 判断必须的属性是否设置了
     * @return boolean
     */
    public boolean isValid() {
        if (isBlank(toAddress))
        {
            return false;
        }

        if (isBlank(messageId))
        {
            return false;
        }
        return true;
    }

    /*****************
     * 业务方法结束
     *****************/

    /*public String getUserNick() {
        return this.userNick;
    }*/

    public String getUserId() {
        return userId;
    }

    public String getToAddress() {
        return toAddress;
    }

    /*   public void setUserNick(String userNick) {
        this.userNick = userNick;
        this.context.put(MessageConstants.KEY_USER_NICK, this.userNick);
    }*/

    public void setUserId(final String userId) {
        this.userId = userId;
        context.put(MessageConstants.KEY_USER_ID, this.userId);
    }

    public void setToAddress(final String toAddress) {
        this.toAddress = toAddress;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(final Map<String, String> context) {
        this.context = context;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(final Integer msgType) {
        this.msgType = msgType;
    }

    /*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}*/

    /*	public String getToAddressIds() {
		return toAddressIds;
	}

	public void setToAddressIds(String toAddressIds) {
		this.toAddressIds = toAddressIds;
	}*/

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public String getToAddressNicks() {
        return toAddressNicks;
    }

    public void setToAddressNicks(final String toAddressNicks) {
        this.toAddressNicks = toAddressNicks;
    }

    public void addToAddress(final String address){
        if(toAddress==null) {
            toAddress = address;
            return ;
        }
        toAddress +="," + address;
    }

    public void addToAddressNicks(final String nick){
        toAddressNicks +="," + nick;
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * 
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank(&quot;&quot;)        = true
     * StringUtil.isBlank(&quot; &quot;)       = true
     * StringUtil.isBlank(&quot;bob&quot;)     = false
     * StringUtil.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     * 
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(final String str)
    {
        int length;

        if ((str == null) || ((length = str.length()) == 0))
        {
            return true;
        }

        for (int i = 0; i < length; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

}
