/**
 * 
 */
package com.taobao.messenger.service.notify;

import com.taobao.messenger.service.common.MessageConstants;

/**
 * 对应MailNotifyAO中的Command输入参数
 * 消息中心前的消息模块旧版本兼容接口，逐步废弃
 * @author afei
 * @version 2008-1-12
 */
@Deprecated
public class MailNotifiee extends MessageNotifiee {
    private static final long serialVersionUID = 3976570945182818003L;
    
    /**
     * 来源地址, 可选
     */
    private String fromAddress;
    
    /**
     * 回复地址, 可选
     */
    private String replyToAddress;
    
    /**
     * 邮件标题覆盖mail.xml中配置, 可选
     */
    private String subject;
    
    /**
     * 邮件编码覆盖mail.xml中配置, 可选
     */
    private String charsetOverride;

    public String getFromAddress() {
        return this.fromAddress;
    }

    public String getReplyToAddress() {
        return this.replyToAddress;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getCharsetOverride() {
        return this.charsetOverride;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        this.getContext().put(MessageConstants.KEY_MAIL_FROM_ADDRESS, this.fromAddress);
    }

    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
        this.getContext().put(MessageConstants.KEY_MAIL_REPLY_TO_ADDRESS, this.replyToAddress);
    }

    public void setSubject(String subject) {
        this.subject = subject;
        this.getContext().put(MessageConstants.KEY_MAIL_SUBJECT, this.subject);
    }

    public void setCharsetOverride(String charsetOverride) {
        this.charsetOverride = charsetOverride;
        this.getContext().put(MessageConstants.KEY_MAIL_CHARSET_OVERRIDE, this.charsetOverride);
    }
}
