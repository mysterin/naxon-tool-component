package com.naxon.tool.http;

import okhttp3.Request;

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
        Request request = new Request.Builder().url(url).build();
        return request;
    }
}
