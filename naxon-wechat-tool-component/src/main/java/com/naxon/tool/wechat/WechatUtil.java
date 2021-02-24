package com.naxon.tool.wechat;

import com.alibaba.fastjson.JSONObject;
import com.naxon.tool.common.DateUtil;
import com.naxon.tool.common.JsonUtil;
import com.naxon.tool.wechat.aes.AesException;
import com.naxon.tool.wechat.aes.SHA1;
import com.naxon.tool.wechat.model.WechatMsgModel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.text.MessageFormat;

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
    public static WechatMsgModel parseXmlMsg(String xmlMsg) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xmlMsg));
        Element root = document.getRootElement();
        JSONObject json = new JSONObject();
        for (Object obj : root.elements()) {
            Element element = (Element) obj;
            String name = element.getName();
            String text = element.getText();
            json.put(name, text);
        }
        WechatMsgModel wechatMsgModel = JsonUtil.parseJson(json.toJSONString(), WechatMsgModel.class);
        return wechatMsgModel;
    }

    /**
     * 回复文本消息
     * @param toUser
     * @param fromUser
     * @param content
     * @return
     */
    public static String replyText(String toUser, String fromUser, String content) {
        String template = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[{3}]]></Content>\n" +
                "</xml>";

        String timestamp = DateUtil.getTimestampStr();
        String msg = MessageFormat.format(template, toUser, fromUser, timestamp, content);
        return msg;
    }

}
