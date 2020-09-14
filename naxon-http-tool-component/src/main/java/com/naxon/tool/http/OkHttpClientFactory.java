package com.naxon.tool.http;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * http 客户端工厂
 */
public class OkHttpClientFactory {

    private static final Integer connectTimeout = 5;
    private static final Integer readTimeout = 10;
    private static final Integer writeTimeout = 10;

    private HttpConfig httpConfig;

    public OkHttpClientFactory() {
        this(null);
    }

    public OkHttpClientFactory(HttpConfig httpConfig) {
        if (httpConfig == null) {
            httpConfig = defaultConfig();
        }
        this.httpConfig = httpConfig;
    }

    public HttpConfig defaultConfig() {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setConnectTimeout(connectTimeout);
        httpConfig.setReadTimeout(readTimeout);
        httpConfig.setWriteTimeout(writeTimeout);
        return httpConfig;
    }

    /**
     * 构造 okhttpClient
     * @return
     */
    public OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.SECONDS);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }
}
