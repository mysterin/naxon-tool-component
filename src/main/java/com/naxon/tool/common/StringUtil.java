package com.naxon.tool.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 字符串工具类
 */
public class StringUtil {

    public static Boolean isEmpty(String s) {
        return org.apache.commons.lang3.StringUtils.isEmpty(s);
    }

    public static Boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static String getString(Object obj) {
        return Objects.toString(obj, "");
    }

    public static String[] split(String text, String separator) {
        String[] strs = new String[]{};
        if (isNotEmpty(text)) {
            strs = text.split(separator);
        }
        return strs;
    }

    public static List<String> splitList(String text, String separator) {
        String[] strs = split(text, separator);
        List<String> list = Arrays.asList(strs);
        return list;
    }

    public static List<String> parseList(String[] strs) {
        List<String> list = Arrays.asList(strs);
        return list;
    }

    public static String[] parseArray(List<String> list) {
        int size = list.size();
        String[] strs = new String[size];
        strs = list.toArray(strs);
        return strs;
    }
}

