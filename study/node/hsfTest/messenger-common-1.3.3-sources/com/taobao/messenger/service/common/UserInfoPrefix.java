package com.taobao.messenger.service.common;
/**
 * 
 * @author huohu
 * @since 1.0, 2009-4-15 下午05:07:06
 */

public enum UserInfoPrefix {
	

	
	/**
	 * 发送用户ID
	 */
	UID("[user.id]"),	
	/**
	 * 发送用户昵称
	 */
	NICK("[user.nick]"),	
    /**
     * 发送用户邮箱
     */
    MAIL("[user.mail]"),
	/**
	 * 发送用户手机号码
	 */
	PHONE("[user.phone]"),
	/**
	 * 发送人群标签
	 */
	TAG("[user.tag]");
	
	private String value;
	private UserInfoPrefix(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public String toString(){
		return value;
	}

}
