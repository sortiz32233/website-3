package com.taobao.messenger.service.common;
/**
 * 日志的常量
 * @author cenjian
 *
 */
public class LogConstants
{
    
   /*
    * Task_id
    */
   public static final String STR_TASKID=" TASKID: ";
   /*
    * Errorcode
    */
   public static final String STR_ERROR_CODE=" ERRORCODE: ";

   /*
    * target tag
    */
   public static final String STR_TARGET_TAG=" TARGET_TAG: ";
   /*
    * on channels
    */
   public static final String STR_CHANNELS=" CHANNELS: ";
  
   /*
    * MESSAGE_TYPE 缺失
    */
   public static final String MESSAGETYPE_MISSING="100001";
   /**
    * 链接超时，不发送
    */
   public static final String TIMEOUT_INGORE="100003";
   /**
    * send channel is null
    */
   public static final String CHANNEL_NULL="100005";
   /**
    * send channel is null
    */
   public static final String INVALID_TARGET_TAG="100007";
   /**
    * message exception
    */
   public static final String MESSAGE_EXCEPTION="100009";
   /**
    * RUNTIME ERROR
    */
   public static final String RUNTIME_ERROR="100011";
   
   /**
    * SUBTASK ERROR
    */
   public static final String SUBTASK_ERROR="100013";
   /**
    * Give up message sending on channel:
    */
   public static final String INVALID_CHANNEL="100015";

    
}
