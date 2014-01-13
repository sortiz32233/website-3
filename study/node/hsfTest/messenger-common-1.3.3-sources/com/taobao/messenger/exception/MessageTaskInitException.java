/**
 * 
 */
package com.taobao.messenger.exception;


/**
 * Task校验异常类
 * @author tianhu
 *
 */
public class MessageTaskInitException extends MessageException {
    private static final long serialVersionUID = -5047597081699757657L;
    
    public MessageTaskInitException(){
		super("task invaild error");
	}
	
	public MessageTaskInitException(String msg){
		super("task invaild error:" + msg);
	}
	
	public MessageTaskInitException(Throwable cause){
		super("task invaild error:", cause);
	}
	
	public MessageTaskInitException(String msg, Throwable cause){
		super("task invaild error:" + msg, cause);
	}

}
