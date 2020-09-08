package com.naxon.tool.common;

/**
 * @author naxon
 * 通用工具类
 */
public class NaxonUtils {

    public static Boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        }
        return false;
    }

    public static Boolean hasEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

}
