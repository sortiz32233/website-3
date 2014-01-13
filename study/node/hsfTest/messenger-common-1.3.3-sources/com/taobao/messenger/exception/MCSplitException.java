package com.taobao.messenger.exception;


/**
 *
 * @author wuxiang
 * @since 2009-04-23 11:09:22
 */
public class MCSplitException extends MessageException {


	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public MCSplitException(){
		super();
	}

	public MCSplitException(String msg){
		super(msg);
	}

	public MCSplitException(Throwable cause){
		super(cause);
	}

	public MCSplitException(String msg, Throwable cause){
		super(msg,cause);
	}

}
