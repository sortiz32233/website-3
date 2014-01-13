/**
 * 
 */
package com.taobao.messenger.exception;

/**
 * 消息模块的异常类
 * @author afei
 * @version 2008-1-12
 */
public class MessageException extends Exception {
    private static final long serialVersionUID = -5047597081699757657L;
    
    public MessageException(){
		super();
	}
	
	public MessageException(String msg){
		super(msg);
	}
	
	public MessageException(Throwable cause){
		super(cause);
	}
	
	public MessageException(String msg, Throwable cause){
		super(msg,cause);
	}

}
