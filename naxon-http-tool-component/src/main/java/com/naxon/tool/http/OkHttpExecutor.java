package com.naxon.tool.http;

import okhttp3.*;

import java.io.IOException;

/**
 * okhttp 执行器
 */
public class OkHttpExecutor {
    private OkHttpClient okHttpClient;

    public OkHttpExecutor(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * 同步 get 请求
     * @param url
     * @return
     * @throws IOException
     */
    public String syncGet(String url) throws IOException {
        Request request = RequestBuilder.build(url);
        Response response = syncExecute(request);
        String body = response.body().string();
        return body;
    }

    /**
     * 同步执行
     * @param request
     * @return
     * @throws IOException
     */
    public Response syncExecute(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }
}
