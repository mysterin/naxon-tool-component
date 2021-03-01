package com.naxon.tool.http;

import com.alibaba.fastjson.JSONObject;
import com.naxon.tool.common.JsonUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * okhttp 执行器
 */
public class OkHttpExecutor {
    private OkHttpClient okHttpClient;

    public OkHttpExecutor(OkHttpClientFactory okHttpClientFactory) {
        this.okHttpClient = okHttpClientFactory.getClient();
    }

    public OkHttpExecutor(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * 注释 httpGet
     */
    public String httpGet(String url) throws IOException {
        return syncGet(url);
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
     * 返回 json
     * @param url
     * @return
     * @throws IOException
     */
    public JSONObject syncGetJson(String url) throws IOException {
        String res = syncGet(url);
        JSONObject json = JsonUtil.parseJson(res);
        return json;
    }

    /**
     * 同步 post 请求，Content-Type=applicatio/json
     * @param url
     * @param params
     * @return
     */
    public String syncPost(String url, Map<String, String> params) throws IOException {
        Request request = RequestBuilder.buildJson(url, params);
        Response response = syncExecute(request);
        String body = response.body().string();
        return body;
    }

    /**
     * 返回 json
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public JSONObject syncPostJson(String url, Map<String, String> params) throws IOException {
        String res = syncPost(url, params);
        JSONObject json = JsonUtil.parseJson(res);
        return json;
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
