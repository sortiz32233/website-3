package com.taobao.messenger.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 手机类工具
 * 
 * @author tianhu E-mail:
 * @version 创建时间：2009-5-29 下午03:03:26
 */
public class MobilePhoneUtil {

	// 短信号码验证，包括国际短信
    private static final Map<String, String> validatorsMap = new HashMap<String, String>();   
    
    static {
    	validatorsMap.put("China", "^[0-1]\\d{9,12}$");
    	validatorsMap.put("Hongkong", "^(852){1}[1,5,6,9](?:\\d{7}|\\d{8}|\\d{12})$");
    	validatorsMap.put("Macau", "^(853){1}[6]\\d{7}$");
    	validatorsMap.put("Taiwan", "^(886){1}[6,7,9](?:\\d{7}|\\d{8}|\\d{10})$");
    	validatorsMap.put("SouthKorea", "^(82){1}[7,1](?:\\d{8}|\\d{9})$");
    	validatorsMap.put("Japan", "^(81){1}[7,8,9](?:\\d{8}|\\d{9})$");
    }
    
    /**
     * 判定手机号码是否合法 {@link #PHONE_VAILD_CHECK_REGEX}
     * 
     * @param address 待判定手机号码
     * @return
     */
	public static boolean isVaildAdress(final String address) {
		try {
			if (null != address && validatePhone(address)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	private static boolean validatePhone(String address) {
		for (String key : validatorsMap.keySet()) {
			if (address.matches(validatorsMap.get(key))) {
				if (Long.parseLong(address) > 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(final String[] args) {

		// should true
		System.out.println(("13632530792").matches(validatorsMap.get("China")));
		System.out.println(("02888071466").matches(validatorsMap.get("China")));
		System.out.println(("075588071466").matches(validatorsMap.get("China")));
		System.out.println(("15867190580").matches(validatorsMap.get("China")));

		// should fasle
		System.out.println(("skyfox@1..com").matches(validatorsMap.get("China")));
		System.out.println(("0123").matches(validatorsMap.get("China")));
		System.out.println(("a13632530792").matches(validatorsMap.get("China")));
		System.out.println(("13632530792b").matches(validatorsMap.get("China")));
		System.out.println(("-1").matches(validatorsMap.get("China")));
		System.out.println(("01231245412").matches(validatorsMap.get("China")));
		System.out.println(("91231245412").matches(validatorsMap.get("China")));

		System.out.println(isVaildAdress("85261600777"));
	}
}