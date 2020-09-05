package com.naxon.tool.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * json 工具类
 */
public class JsonUtils {

    public static JSONObject parseJson(String text) {
        JSONObject jsonObject = parseJson(text, JSONObject.class);
        return jsonObject;
    }

    public static <T> T parseJson(String text, Class<T> clazz) {
        T result = JSONObject.parseObject(text, clazz);
        return result;
    }

    public static <T> List<T> parseJsonToList(String text, Class<T> clazz) {
        List<T> list = JSONObject.parseArray(text, clazz);
        return list;
    }

    public static String toJsonString(Object obj) {
        String s = JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
        return s;
    }
}
