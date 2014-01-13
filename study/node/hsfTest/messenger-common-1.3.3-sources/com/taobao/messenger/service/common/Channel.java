package com.taobao.messenger.service.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 通道类型枚举类 全部通道(0xFFFFFFFF),无通道(0x0) <BR>
 * 通过邮件发送 MAIL(0x0001), 通过短信发送 SMS(0x0002), 通过站内信发送 PMSG(0x0004); 通过旺旺消息发送
 * WANGWANG(0x0008),
 * 
 * @author huohu
 * @since 1.0, 2009-4-7 下午02:30:18
 */

public enum Channel {
	/**
	 * 无通道
	 */
	NONE(0x0),
	/**
	 * 根据MessageType中的通道类型设定
	 */
	AUTO(0xFFFFFFFF),
	/**
	 * 通过邮件发送
	 */
	MAIL(0x0001),
	/**
	 * 通过旺旺消息发送
	 */
	WANGWANG(0x0008),
	/**
	 * 通过短信发送
	 */
	SMS(0x0002),
	/**
	 * 通过站内信发送
	 */
	PMSG(0x0004);

	private int value;

	private Channel(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return this.name();
	}

	/**
	 * 将int转换为对应的channel枚举变量
	 * 
	 * @param value
	 *            channle对应的int
	 * @return 对应channel枚举变量，无对应channel将返回null
	 */
	public static Channel parseChannel(int value) {
		for (Channel c : Channel.values()) {
			if (c.getValue() == value) {
				return c;
			}
		}
		return null;
	}

	/**
	 * 从int解析出所包括的所有channel
	 * 
	 * @param value
	 *            待解析的channels组合int值
	 * @return 对应的channel序列，无对应channel将返回size为0的List
	 */
	public static List<Channel> parseChannels(int value) {
		List<Channel> channels = new ArrayList<Channel>();
		for (Channel c : Channel.values()) {
			if (c.equals(Channel.AUTO)) {
				continue;
			}
			if ((c.getValue() & value) > 0) {
				channels.add(c);
			}
		}
		return channels;
	}

	/**
	 * 将Channel枚举序列组合为对应的int值。<br>
	 * 如List包括了旺旺(0x08)通道,邮件(0x01)通道，则返回为 0x08|0x01=0x09
	 * 
	 * @param channels
	 *            待组合的Channel序列
	 * @return 组合后的int值
	 */
	public static int toValue(List<Channel> channels) {
		int channelInt = 0;
		for (Channel channel : channels) {
			channelInt = channelInt | channel.getValue();
		}
		return channelInt;
	}

	public static void main(String[] dd) {
		System.out.println(Channel.valueOf("WANGWANG"));
	}
}
