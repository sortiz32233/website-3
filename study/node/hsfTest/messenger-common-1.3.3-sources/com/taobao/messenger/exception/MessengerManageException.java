package com.taobao.messenger.exception;

/**
 * 控制台管理异常类<br>
 * 将异常的message以及cause拼接为字符串抛出，避免将无法反序列化Throwable抛出到客户端
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-4-27 上午02:20:55
 */
// TODO: 待添加
public class MessengerManageException extends Exception {
	private static final long serialVersionUID = 3066155589655543589L;

	public MessengerManageException() {
		super();
	}

	public MessengerManageException(final String msg) {
		super(msg);
	}

	/**
	 * 将异常message以及cause拼接为字符串抛出，避免将无法反序列化Throwable抛出到客户端
	 * 
	 * @param cause
	 */
	public MessengerManageException(final Throwable cause) {
		super(transCauseToMessage(cause));
	}

	/**
	 * 将message、异常message以及cause拼接为字符串抛出，避免将无法反序列化Throwable抛出到客户端
	 * 
	 * @param msg
	 * @param cause
	 */
	public MessengerManageException(final String msg, final Throwable cause) {
		super(msg + transCauseToMessage(cause));
	}

	/**
	 * 将异常message以及cause拼接为字符串
	 * 
	 * @param cause
	 * @return
	 */
	private static String transCauseToMessage(final Throwable cause) {
		final StringBuffer stringBuffer = new StringBuffer();
		if (cause.getMessage() != null) {
			stringBuffer.append(cause.getMessage());
		}
		if (cause.getCause() != null && cause.getCause().getMessage() != null) {
			stringBuffer.append("cause:" + cause.getCause().getMessage());
		}
		return stringBuffer.toString();
	}
}
