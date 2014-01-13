package com.taobao.messenger.task.impl;

import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.common.UserInfoPrefix;
import com.taobao.messenger.task.MessageTask;
import com.taobao.messenger.utils.MailUtil;

/**
 * 发送邮件任务，邮件列表需非淘宝用户已使用邮箱
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-4-30 上午12:28:19
 * @see MessageTask
 * @see DefaultMessageTask
 * @see MobileMessageTask
 */
public class MailMessageTask extends DefaultMessageTask
{
    private static final long serialVersionUID = 5712631063971562132L;

    /**
     * 初始化发送邮件任务
     */
    public MailMessageTask()
    {
        super(UserInfoPrefix.MAIL);
    }


    @Override
    final protected boolean isTargetValid(final String mail) throws MessageTaskInitException
    {
        //TODO:待加入对邮箱判定是否为已注册用户
        if (!super.getUserInfoPrefix().equals(UserInfoPrefix.MAIL.getValue()))
        {
            throw new MessageTaskInitException("Task tag expect MAIL,but is " + getUserInfoPrefix());
        }

        if (!MailUtil.isVaildAdress(mail))
        {
            throw new MessageTaskInitException("Target MAIL is invalid:" + mail);
        }
        return true;
    }


}
