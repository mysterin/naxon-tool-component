package com.naxon.tool.common;

/**
 * @author naxon
 * 通用工具类
 */
public class NaxonUtils {

    public static Boolean isEmpty(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        }
        return true;
    }

    public static Boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
