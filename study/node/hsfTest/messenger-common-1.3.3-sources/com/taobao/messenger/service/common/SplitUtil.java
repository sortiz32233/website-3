/*
 *  
 *
 */

package com.taobao.messenger.service.common;


import java.util.HashMap;
import java.util.Map;

import com.taobao.messenger.exception.MCSplitException;

/**
 *
 * @author wuxiang
 * @since 2009-04-23 13:51:00
 */
public class SplitUtil {
    
	/** 内部分隔号 */
    public static final String INNER_SPLIT_MARK = "=";
    /** 外部分隔号 冒号分隔 */
    public static final String OUTER_SPLIT_MARK= ";";
    /** 外部分隔号 逗号分隔 */
    public static final String OUTER_SPLIT_MARK_COMMA = ",";
    
    
    /**
     * 分隔字符串成Map
     * 默认 以;为外部分隔符号，以=为内部分隔符号
     * 
     * @param tagContent eg: id=11;name=ee
     * @return Map<String, String>
     * @throws MCSplitException
     */
    public static Map<String, String> splitStringToMap(String tagContent) throws MCSplitException {
        return splitStringToMap(tagContent,OUTER_SPLIT_MARK,INNER_SPLIT_MARK);
    }
    
    /**
     * 分隔字符串成Map
     * 
     * @param tagContent
     * @param outerSplit   外部分隔号
     * @param innerSplit   内部分隔号
     * @return
     * @throws MCSplitException
     */
    public static Map<String, String> splitStringToMap(String tagContent, String outerSplit, String innerSplit) throws MCSplitException {
        if (tagContent == null || tagContent.trim().length() == 0) {
            return null;
        }
        Map<String, String> content = new HashMap<String, String>();
        try {
            String[] outer = tagContent.split(outerSplit);
            
            for (int i = 0; i < outer.length; i++) {
            	String innerStr = outer[i];
                String[] inner = innerStr.split(innerSplit,2);
                if(inner.length > 1){
                	content.put(inner[0], inner[1]);
                }
                
            }
        
            return content;
        } catch (Exception e) {
            throw new MCSplitException(e);
        }
    }
    
    /**
     * 以=为内部分隔号
     * 
     * @param target
     * @return String[]
     * @throws MCSplitException
     */
    public static String[] splitTagTarget(String target) throws MCSplitException {
        if (target == null) {
            return null;
        }
        try {
            String[] inner = target.split(INNER_SPLIT_MARK);
            return inner;
        } catch (Exception e) {
            throw new MCSplitException(e);
        }

    }


}
