package com.naxon.tool.wechat;

import com.alibaba.fastjson.JSONObject;
import com.naxon.tool.http.OkHttpExecutor;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/3/2 16:55
 */
public class WechatUtil {

    private static OkHttpExecutor okHttpExecutor = new OkHttpExecutor();

    /**
     * 获取 Access Token
     */
    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    /**
     * 获取 accessToken
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getAccessToken(String appId, String appSecret) throws IOException {
        String url = MessageFormat.format(GET_ACCESS_TOKEN_URL, appId, appSecret);
        JSONObject json = okHttpExecutor.syncGetJson(url);
        if (json.containsKey("access_token")) {
            return json.getString("access_token");
        }
        throw new RuntimeException("获取 accessToken 失败，result=" + json);
    }

}
