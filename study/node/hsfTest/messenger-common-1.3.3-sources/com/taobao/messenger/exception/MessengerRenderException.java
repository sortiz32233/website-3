package com.taobao.messenger.exception;

/**
 * 模板渲染异常类
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-4-27 上午02:20:55
 */

public class MessengerRenderException extends Exception
{

    private static final long serialVersionUID = 4042882280778010609L;

    public MessengerRenderException()
    {
        super("模板渲染失败");
    }

    public MessengerRenderException(final String msg)
    {
        super("模板渲染失败:" + msg);
    }

    public MessengerRenderException(final Throwable cause)
    {
        super("模板渲染失败", cause);
    }

    public MessengerRenderException(final String msg, final Throwable cause)
    {
        super("模板渲染失败:" + msg, cause);
    }
}
