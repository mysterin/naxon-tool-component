package com.naxon.tool.wechat;

import com.naxon.tool.wechat.aes.AesException;
import com.naxon.tool.wechat.aes.SHA1;
import jdk.nashorn.internal.ir.ReturnNode;

import java.util.Map;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/23 10:33
 */
public class WechatUtil {
    /**
     * 接入服务器时签名校验
     * @param token
     * @param msgSignature
     * @param timeStamp
     * @param nonce
     * @return
     * @throws AesException
     */
    public static Boolean checkSignature(String token, String msgSignature, String timeStamp, String nonce) throws AesException {
        String signature = SHA1.getSHA1(token, timeStamp, nonce);
        return signature.equals(msgSignature);
    }

    /**
     * 解析微信消息
     * @return
     */
    public static Map<String, String> parseXmlMsg() {
        return null;
    }

}
