package com.taobao.messenger.service;

import com.taobao.messenger.exception.MessageException;

public interface MessageSenderWebService {
	
	/**
	 * WEB SERVICE简单接口
	 * @param address			根据通道设置的情况 填充收件人地址
	 * @param subject			模板主题中的变量KEY-VALUE对，格式 key1:value1;key2:value2
	 * @param content			模板内容中的变量KEY-VALUE对，格式 key1:value1;key2:value2
	 * @param channel			四个通道数字标识  1, 2, 4, 8 	 分别对应  	邮件,短信,站内信,旺旺 
	 * @param sourceId			调用应用标示
	 * @param templateId		模板ID
	 * @param messageTypeId 	消息类型ID
	 * @return
	 * @throws MessageException
	 */
	String  send(String address, String subject, String content,
			String channel, String sourceId,String templateId,String messageTypeId) throws MessageException;

}
