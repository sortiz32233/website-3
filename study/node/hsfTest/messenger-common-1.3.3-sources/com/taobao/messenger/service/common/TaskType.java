package com.taobao.messenger.service.common;
/**
 * 任务类型
 * @author huohu
 * @since 1.0, 2009-5-7 下午05:30:35
 */
public enum TaskType {
	
	/**
	 * 默认任务类型
	 */
	DEFAULT(1);
	
	private int value;
	private TaskType(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}

}
