package com.naxon.tool.common.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegexPattern {

    /**
     * 手机号
     */
    public static Pattern mobilePattern = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$");

    /**
     * 18 位身份证
     */
    public static Pattern identityCardPattern = Pattern.compile("^((\\d{18})|([0-9x]{18})|([0-9X]{18}))$");
}
