package com.taobao.messenger.service;

public interface PmsgReaderService {

	/**
	 * 根据用户数字ID查询INBOX表中没有阅读的站内信数量
	 * @param recipientId 用户数字ID
 	 * @return Integer 返回未读 站内信数量  若查询异常 返回-1
	 */
   public Integer  queryInboxPmsgCountNotReadByRecipientId(Long recipientId);
   
   /**
    * 根据用户昵称查询INBOX表中没有阅读的站内信数量
    * @param nick 
    * @return Integer 返回未读站内信数量 若查询异常 返回-1
    */
   public Integer  queryInboxPmsgCountNotReadByNick(String nick);
}
