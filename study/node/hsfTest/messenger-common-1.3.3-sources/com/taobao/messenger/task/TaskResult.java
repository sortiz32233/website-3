package com.taobao.messenger.task;

import java.io.Serializable;

/**
 * 用于消息发送任务结果返回
 * 附带任务对应唯一id
 * @author huohu
 * @since 1.0, 2009-4-3 上午10:33:00
 */
public interface TaskResult extends Serializable{

	/**
	 * 获取taskID
	 * @return  任务ID
	 */
	public long getTaskID();

    /**
     * 设置taskID
     * @param taskID
     */
    public void setTaskID(long taskID);

	/**
	 * 判定任务接收是否成功
	 * @return  任务是否被成功送入MessageCenter
	 */
	public boolean isSuccess();
	/**
	 * 获取返回结果信息
	 * @return  获取返回给应用的信息
	 */
	public String getInfo();
	
	/**
	 * 设置结果为失败并记录失败信息
	 * @param info 失败信息
	 */
	public void setFailure(String info);
	
	/**
	 * 设置返回信息
	 * @param info 返回信息
	 */
	public void setInfo(String info);
	
	/**
	 * 设置结果为成功 
	 */
	public void setSuccess();
}
