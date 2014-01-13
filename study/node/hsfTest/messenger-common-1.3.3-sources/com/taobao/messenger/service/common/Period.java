package com.taobao.messenger.service.common;
/**
 * 疲劳度周期
 * @author huohu
 * @since 1.0, 2009-5-7 下午05:30:35
 */
public enum Period {
	
	/**
	 * 天
	 */
	ONEDAY(1),
	/**
	 * 周（七天）
	 */
	ONEWEEK(7),
	/**
	 * 月（31天）
	 */
	ONEMONTH(31);
	
	private int value;
	private Period(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}

}
