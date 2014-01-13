package com.taobao.messenger.task.impl;

import com.taobao.messenger.exception.MessageTaskInitException;
import com.taobao.messenger.service.common.UserInfoPrefix;
import com.taobao.messenger.task.MessageTask;

/**
 * 发送nick任务<br>
 * 填入target为用户nick,需为淘宝用户,一任务支持同时发送{@value #NICK_UPPER_LIMIT}个用户<br>
 * 建议尽量使用{@link DefaultMessageTask}
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-4-30 上午12:28:19
 * @see MessageTask
 * @see DefaultMessageTask
 * @see MobileMessageTask
 * @see MailMessageTask
 */
public class NickMessageTask extends DefaultMessageTask
{
    private static final int MAX_NICK_LENGTH = 32;
    private static final long serialVersionUID = 5712631063971562132L;

    private static final int NICK_UPPER_LIMIT = 20;

    /**
     * 初始化发送邮件任务
     */
    public NickMessageTask()
    {
        super(UserInfoPrefix.NICK);
    }


    @Override
    final protected boolean isTargetValid(final String nick) throws MessageTaskInitException
    {
        if (!super.getUserInfoPrefix().equals(UserInfoPrefix.NICK.getValue()))
        {
            throw new MessageTaskInitException("Task tag expect MAIL,but is " + getUserInfoPrefix());
        }

        if (!isVaildAdress(nick))
        {
            throw new MessageTaskInitException("Target Nick is invalid:" + nick);
        }

        return true;
    }


    private boolean isVaildAdress(final String nick) throws MessageTaskInitException
    {
        if (nick == null)
        {
            return false;
        }
        if (nick.length() == 0 || nick.length() > MAX_NICK_LENGTH)
        {
            return false;
        }
        if (nick.contains(TARGET_SPLIT))
        {
            throw new MessageTaskInitException("Target Nick :" + nick + " can't include '" + TARGET_SPLIT + "'");
        }
        return true;
    }


}
