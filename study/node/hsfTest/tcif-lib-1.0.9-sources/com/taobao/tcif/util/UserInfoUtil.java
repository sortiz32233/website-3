package com.taobao.tcif.util;

import java.util.HashMap;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
/**
 * UserInfo 工具类
 * @author lizhe.zcq
 *
 */
public class UserInfoUtil
{
	public static JSONDeserializer<HashMap<String,Object>> Deserializer  = new JSONDeserializer<HashMap<String,Object>>();
	public static JSONSerializer Serializer  = new JSONSerializer();
	/**
	 * 反序列化UserInfo信息
	 * @param  userInfo 用户信息字符串
	 * @return 返回用户信息的hashmap
	 */
	public static HashMap<String,Object> DecodeUserInfo(String userInfo){
		return Deserializer.deserialize( userInfo );
	}
	/**
	 * 序列化userInfo
	 * @param userInfo
	 * @return
	 */
	public static String EncodeUserInfo(HashMap<String,Object> userInfo){
		return Serializer.deepSerialize( userInfo );
	}
}
