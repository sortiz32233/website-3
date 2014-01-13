package com.taobao.messenger.service;

import java.util.Map;

import com.taobao.messenger.exception.MessageException;
import com.taobao.messenger.service.common.Channel;
import com.taobao.messenger.service.notify.MessageNotifiee;
import com.taobao.messenger.task.MessageTask;
import com.taobao.messenger.task.TaskResult;

/**
 * 消息发送服务<br>
 * 配置方式：<br>
 * <code>
 * {@code
 * <bean id="messageSenderService"
 *      class="com.taobao.hsf.app.spring.util.HSFSpringConsumerBean"
 *      init-method="init">
 *   <property name="interfaceName">
 *      <value>com.taobao.messenger.service.MessageSenderService</value>
 *   </property>
 * </bean>
 * }
 * </code>
 */
public interface MessageSenderService {

	/***************************************************************************
	 * 旧发送模块消息发送接口,已废弃
	 * 
	 * @param targets
	 *            Map<String, MessageNotifiee> target 消息发送对象
	 *            {("wangwang",messageTarget),("mail",mailTarget),("arkWangwang"
	 *            ,messageTarget),("sms",messageTarget),("pmsg",messageTarget)
	 *            ...}
	 * @param messageId
	 *            String 消息配置ID<message/>或者<mail/>
	 * @param context
	 *            Map<String, Object>
	 *            消息模板和NotifyAO中用到的参数(DefaultMessageNotifyAO/MailNotifyAO)
	 * @exception MessageException
	 **************************************************************************/
	@Deprecated
	void send(Map<String, MessageNotifiee> targets) throws MessageException;

	/**
	 * 发送即时消息任务
	 * 
	 * @param task
	 *            {@link MessageTask} 包含目标用户targets、发送模板参数、发送延时等信息的 消息发送任务对象
	 * @return {@link TaskResult} 任务是否成功接收。
	 *         <ul>
	 *         <li>已经超出系统负载能力等异常时将返回失败,通过{@link TaskResult#getInfo()}获取失败原因;</li>
	 *         <li>若成功接收将返回任务对应id，可通过 {@link TaskResult#getTaskID()}获取</li>
	 *         </ul>
	 * @throws MessageException
	 *             如传入参数非法、运行期异常将抛出
	 */

	TaskResult send(MessageTask task) throws MessageException;

	/**
	 * 提交延时执行任务消息
	 * 
	 * @param task
	 *            {@link MessageTask} 包含目标用户targets、发送模板参数、发送延时等信息的 消息发送任务对象
	 * @return {@link TaskResult} 任务是否成功接收。
	 *         <ul>
	 *         <li>已经超出系统负载能力等异常时将返回失败,可通过{@link TaskResult#getInfo()}获取失败原因;</li>
	 *         <li>若成功接收将返回任务对应id，可通过 {@link TaskResult#getTaskID()}获取</li>
	 *         </ul>
	 * @throws MessageException
	 *             如传入参数非法、运行期异常将抛出
	 */
	TaskResult submit(MessageTask task) throws MessageException;

	/**
	 * 及时发送
	 * @param address  发送地址, 手机号码，或者 邮件地址，或者旺旺号
	 * @param subject  发送主题
	 * @param content  发送内容
	 * @param channel  通道选择
	 * @param sourceId  发送源地址
	 * @return
	 * @throws MessageException
	 */
	TaskResult send(String address, String subject, String content,
			Channel channel, String sourceId) throws MessageException;
	
}
