package com.naxon.tool.common;

/**
 * 字符串工具类
 */
public class StringUtils {

    public static Boolean isEmpty(String s) {
        return org.apache.commons.lang3.StringUtils.isEmpty(s);
    }

    public static Boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
