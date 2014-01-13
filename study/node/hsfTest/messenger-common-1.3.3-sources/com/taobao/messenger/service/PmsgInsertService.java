package com.taobao.messenger.service;

import java.util.Date;
import java.util.List;

public interface PmsgInsertService {
	/**
	 * 用于站内信压力测试
	 * @param recipient_id
	 * @param recipient_nick
	 * @param sender_id
	 * @param sender_nick
	 * @param subject
	 * @param body
	 * @return
	 */
	public long sendPmsgPerformance(long pmsg_id,long recipient_id,String recipient_nick,
			long sender_id,String sender_nick,
			String subject,String body,
			Date gmt_create,
			Date gmt_modified,
			byte status,
			byte deleted,
			String send_ip);
	
	

  public long sendDraftPmsg(long pmsg_id,long sender_id,
		  String sender_nick,long recipient_id,String recipient_nick,String subject ,String body,Date gmt_create,Date gmt_modified,byte status,byte deleted);
	/**
	 * 根据pmsg_id
	 * @param pmsg_id
	 * @return
	 */
	public int updatePmsgStatus(long pmsg_id);
	
	
	/**
	 * 根据主键id查询pmsg
	 * @param pmsg_id
	 * @return
	 */
	public Object selectPmsgById(long pmsg_id);
	
	/**
	 * 根据recipientId查询站内信数量
	 * @param recipientId
	 * @return
	 */
	public int countPmsgByRecientId(long recipientId);
	
	/**
	 * 按照主键id批量删除
	 * @param pmsgIds
	 * @return
	 */
	public int deletePmsgChecked(String pmsgIds);
	
	
	public List<Object> selectPmsgsByRecipientId(long recipientId,boolean isAdmin,int startNum);
	
	public boolean sendEmailPerformance(String mailFrom,String mailTo,String subject,String content);

	
	public void downloadFile();
}
