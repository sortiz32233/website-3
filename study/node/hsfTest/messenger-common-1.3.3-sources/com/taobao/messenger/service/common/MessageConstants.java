package com.taobao.messenger.service.common;

/**
 * 消息模板渲染模块的常量定义接口
 * 
 * @author afei
 * @version 2008-1-12
 */
public interface MessageConstants {
	/*********************************
	 * 杂项常量定义
	 *********************************/

	/**
	 * 发送消息调用AO超时记录日志(毫秒)
	 */
	long INVOKE_AO_TIME_OUT = 60 * 1000;

	/**
	 * 发送消息超时记录日志(毫秒)
	 */
	long INVOKE_SENDER_TIME_OUT = 3 * 1000;

	/*********************************
	 * messages.xml中标签名称定义
	 *********************************/
	String KEY_MESSAGES = "messages";
	String KEY_MESSAGE = "message";
	String KEY_ID = "id";
	String KEY_CHARSET = "charset";
	String KEY_FROM = "from";
	String KEY_SUBJECT = "subject";
	String KEY_TEMPLATE = "template";
	String KEY_CONTENT = "content";
	String KEY_MEMO = "memo";

	/*********************************
	 * 放入TemplateContext时使用的键定义
	 *********************************/
	String KEY_USER_ID = "userId";
	String KEY_USER_NICK = "userNick";
	String KEY_MESSAGE_ID = "message_id";
	String KEY_MESSAGE_FROM = "message_from";
	String KEY_MESSAGE_SUBJECT = "message_subject";

	/****************************************************
	 * MessageNotifier放入MessageNotifyAO中参数使用的键定义
	 ****************************************************/
	String KEY_AO_MESSAGE_ID = "messageId";
	String KEY_AO_MESSAGE_PARAMS = "messageParams";
	String KEY_AO_MESSAGE_TARGET = "messageTarget";
	String KEY_AO_MESSAGE_CALLBACK = "messageCallback";
	String KEY_AO_MESSAGE_TIME = "messageTime";

	/****************************************************
	 * MessageNotifier放入MailNotifyAO中参数使用的键定义
	 ****************************************************/
	String KEY_MAIL = "mail";
	String KEY_MAIL_ID = "mailId";
	String KEY_MAIL_FROM_ADDRESS = "fromAddress";
	String KEY_MAIL_TO_ADDRESS = "toAddress";
	String KEY_MAIL_REPLY_TO_ADDRESS = "replyToAddress";
	String KEY_MAIL_SUBJECT = "subject";
	String KEY_MAIL_CHARSET_OVERRIDE = "charsetOverride";

	/*********************************************************
	 * MessageNotifier调用MessageNotifyAO时查找Command的默认名称
	 *********************************************************/
	String DEFAULT_COMMAND_DISPATCH_NAME = "commandDispatcher";
	String DEFAULT_COMMAND_AO_NAME = "messageNotifyAO";

	/*********************************************************
	 * Message模块消息类型配置在MessageNotifyGateway中
	 *********************************************************/
	String MESSAGE_TYPE_MAIL = "mail";
	String MESSAGE_TYPE_WANGWANG = "wangwang";
	String MESSAGE_TYPE_ARKWANGWANG = "arkWangwang";
	String MESSAGE_TYPE_SMS = "sms";
	String MESSAGE_TYPE_PMSG = "pmsg";

	public static final String DEFAULT_SPLIT_1 = ";"; // 一级分隔符
	public static final String DEFAULT_SPLIT_2 = ":"; // 二级分隔符
	public static final String DEFAULT_SPLIT_3 = ","; // 三级分隔符

	public static final String DEFAULT_SPLIT_FILTER = "_"; // 过来器分隔符
	public static final String DOT_SPLIT = ".";

	public final static String DEFAULT_EXECUTOR = "00000000";

	/*********************************************************
	 * 携带在消息中key-value中的UserInfo人群的地址对应key常量
	 *********************************************************/
	/**
	 * 用户数字id 对应key名
	 */
	public static final String TAG_USER_ID = "USER_ID";
	/**
	 * 用户字符串id对应key名
	 */
	public static final String TAG_STR_ID = "STR_ID";
	/**
	 * 用户nick对应key名
	 */
	public static final String TAG_NICK = "NICK";
	/**
	 * 用户email对应key名
	 */
	public static final String TAG_EMAIL = "EMAIL";
	/**
	 * 发给用户EMail显示的发送者EMail对应key名
	 */
	public static final String TAG_EMAIL_FROM = "EMAIL_FROM";
	/**
	 * 发给用户即使旺旺消息显示的发送者的Nick对应key名
	 */
	public static final String TAG_NICK_FROM = "NICK_FROM";

	/**
	 * 从UIC查到的信息，如果是单个用户的信息用上面的。 多个用户的信息加到USER_INFO_LIST里面，传到后面的任务中
	 */
	public static final String USER_INFO_LIST = "USER_INFO_LIST";
	/**
	 * 用户手机号码对应key名
	 * 
	 */
	public static final String TAG_MOBILE_PHONE = "MOBILE_PHONE";

	/*********************************************************
	 * tair命名空间
	 *********************************************************/
	public static final int TAIR_NP_FAGITUE = 27;
	/**
	 * 消息类型
	 */
	public static final int TAIR_NP_MSG_TYPE = 28;
	/**
	 * 消息模板
	 */

	public static final int TAIR_NP_MSG_TEMPLATE = 29;
	
	
	
	/**
	 * Tair控制变量 namespace
	 */
	public static final int TAIR_NP_MSG_CONTROLLER_KEYVALUE=206;

	/**
	 * 指定为0 不管tair的 version
	 */
	public static final int TAIR_VERSION = 0;

	/*********************************************************
	 * Notify命名空间
	 *********************************************************/
	public static final String NOTIFY_TOPIC = "MESSENGER";
	/**
	 * 任务停止notify消息类型
	 */
	public static final String NT_STOP_TASK = "msg-stop-task";
	/**
	 * 模板更新notify消息类型
	 */
	public static final String NT_TEMPLET_UPDATE = "msg-templet-update";
	/**
	 * 消息类型更新notify消息类型
	 */
	public static final String NT_MSG_TYPE_UPDATE = "msg-type-update";
	
	/**
	 * source刷新notify消息类型
	 */
	public static final String NT_SOURCE_REFRESH = "600-MSGSYNC-SOURCESYNC";
	
	/**
	 * channel刷新notify消息类型
	 */
	public static final String NT_CHANNEL_REFRESH = "500-MSGSYNC-CHANNELSYNC";

	/*********************************************************
	 * 加载和恢复任务偏移当前时间的长度，单位秒
	 *********************************************************/
	public static final long LOAD_INSURE_DATE = 60 * 60 * 24;
	public static final long RENEW_INSURE_DATE = 60 * 60 * 24 * 2;

	/*********************************************************
	 * FIXME 站内信发件人标识 2010-01-13
	 *********************************************************/
	public static final String PMSG_SENDER_NICKNAME = "PMSG_SENDER_NICKNAME";
	public static final String PMSG_SENDER_ID = "PMSG_SENDER_ID";

	/**
	 * 站内信的标识ID
	 */
	public static final String PMSG_TASK_ID = "PMSG_TASI_ID";
	
	/**
	 * 环境标识。 
	 */
	public static final String ENV_PREPUB = "prepub";
	public static final String ENV_DAILY = "daily";
	public static final String ENV_PUB = "online";
	
	/**
	 * ims营销任务sourceId。
	 */
	public static final String SOURCE_IMS_MARKETING = "ims*promotion";
	public static final String SOURCE_WIRELESS_MARKETING = "wireless_galaxy_801@potian";
	
	/**
	 * 用于旺旺做p4p推广的商品id。
	 */
	public static final String WW_P4P_ITEM_KEY = "itemIdForP4p";
	
	public static final String WW_CONNECTION_PRIORITY="connectionPriority";
	
	/**
	 * 淘宝系统站内信分类<key, value>，存放于context中传递过来。
	 */
	public static final String PMSG_TYPE_KEY = "pmsgType";
	public final static Integer PMSG_TYPE_OFFICIAL 	= 1;	// 官方动态
	public final static Integer PMSG_TYPE_REMIND 	= 2;	// 重要提醒
	public final static Integer PMSG_TYPE_SERVICE 	= 3;	// 服务提醒
	public final static Integer PMSG_TYPE_ACTIVITY 	= 4;	// 淘宝活动
	public final static Integer PMSG_TYPE_TRADE 	= 5;	// 交易提醒
}
