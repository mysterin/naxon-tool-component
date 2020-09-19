package com.naxon.tool.http;

import com.naxon.tool.common.JsonUtils;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * okhttp 请求构造器
 */
public class RequestBuilder {

    /**
     * 构造 get 请求
     * @param url
     * @return
     */
    public static Request build(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return request;
    }

    /**
     * 构造 post json 请求
     * @param url
     * @param params
     * @return
     */
    public static Request buildJson(String url, Map<String, String> params) {
        String body = JsonUtils.toJsonString(params);
        RequestBody requestBody = RequestBody.create(MediaType.APPLICATION_JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return request;
    }
}
