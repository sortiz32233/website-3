package com.taobao.messenger.task.impl;


import com.taobao.messenger.task.TaskResult;

/**
 * 
 * @author huohu
 * @since 1.0, 2009-4-7 ÏÂÎç04:01:02
 */
public class DefaultResult implements TaskResult{

    /**
     * 
     */
    private static final long serialVersionUID = 5894269260119969986L;

    private long taskID = 0;
    private boolean isSuccess = true;
    private String info = "";

    public DefaultResult(){
    }

    public DefaultResult(final long taskID){
        this.taskID = taskID;
    }

    public long getTaskID() {
        return taskID;
    }
    public void setTaskID(final long taskID) {
        this.taskID = taskID;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    public void setFailure(final String info) {
        if(info == null)
        {
            throw new IllegalArgumentException("failure info can't be null");
        }
        isSuccess = false;
        this.info = info;
    }

    public void setSuccess() {
        isSuccess = true;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(final String info) {
        this.info = info;
    }

}
