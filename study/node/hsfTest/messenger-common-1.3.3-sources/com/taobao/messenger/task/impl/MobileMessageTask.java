package com.taobao.messenger.task.impl;

import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.common.UserInfoPrefix;
import com.taobao.messenger.task.MessageTask;
import com.taobao.messenger.utils.MobilePhoneUtil;

/**
 * 发送手机短信消息任务，手机列表需非淘宝用户已使用手机
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-4-30 上午12:28:19
 * @see MessageTask
 * @see DefaultMessageTask
 * @see MailMessageTask
 */
public class MobileMessageTask extends DefaultMessageTask
{
    private static final long serialVersionUID = 5712631063971562132L;

    /**
     * 初始化发送手机短信任务
     */
    public MobileMessageTask()
    {
        super(UserInfoPrefix.PHONE);
    }

    @Override
    final protected boolean isTargetValid(final String phone) throws MessageTaskInitException
    {
        // TODO:待加入对手机判定是否为已注册用户
        if (!super.getUserInfoPrefix().equals(UserInfoPrefix.PHONE.getValue()))
        {
            throw new MessageTaskInitException("Task tag expect PHONE,but is " + super.getUserInfoPrefix());
        }
        if (!MobilePhoneUtil.isVaildAdress(phone))
        {
            throw new MessageTaskInitException("Target PHONE is invalid:" + phone);
        }

        return true;
    }

}
