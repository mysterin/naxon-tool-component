package com.naxon.tool.wechat;

import com.alibaba.fastjson.JSONObject;
import com.naxon.tool.common.DateUtil;
import com.naxon.tool.common.JsonUtil;
import com.naxon.tool.wechat.aes.AesException;
import com.naxon.tool.wechat.aes.SHA1;
import com.naxon.tool.wechat.aes.WXBizMsgCrypt;
import com.naxon.tool.wechat.constant.MsgType;
import com.naxon.tool.wechat.model.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author linxiaobin
 * @Description 微信消息工具类
 * @date 2021/2/23 10:33
 */
public class WechatMsgUtil {

    private static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

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
     * 解密微信加密消息
     * @param appId
     * @param token
     * @param aesKey
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param encryptMsg
     * @return
     * @throws AesException
     * @throws DocumentException
     */
    public static WechatMsgModel decryptMsg(String appId, String token, String aesKey,
                                            String msgSignature, String timestamp,
                                            String nonce, String encryptMsg) throws AesException, DocumentException {
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, appId);
        String decryptMsg = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, encryptMsg);
        WechatMsgModel wechatMsgModel = parseXmlMsg(decryptMsg);
        return wechatMsgModel;
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
     * 构建回复文本
     * @param wechatMsgModel
     * @param content
     * @return
     */
    public static WechatReplyMsgModel replyText(WechatMsgModel wechatMsgModel, String content) {
        WechatReplyMsgModel wechatReplyMsgModel = WechatReplyMsgModel.builder()
                .fromUserName(wechatMsgModel.getToUserName())
                .toUserName(wechatMsgModel.getFromUserName())
                .msgType(MsgType.TEXT)
                .content(content)
                .build();
        return wechatReplyMsgModel;
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

    /**
     * 回复图片消息
     * @param toUser
     * @param fromUser
     * @param mediaId
     * @return
     */
    public static String replyImage(String toUser, String fromUser, String mediaId) {
        String template = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[image]]></MsgType>\n" +
                "  <Image>\n" +
                "    <MediaId><![CDATA[{3}]]></MediaId>\n" +
                "  </Image>\n" +
                "</xml>";
        String timestamp = DateUtil.getTimestampStr();
        String msg = MessageFormat.format(template, toUser, fromUser, timestamp, mediaId);
        return msg;
    }

    /**
     * 回复语音消息
     * @param toUser
     * @param fromUser
     * @param mediaId
     * @return
     */
    public static String replyVoice(String toUser, String fromUser, String mediaId) {
        String template = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[voice]]></MsgType>\n" +
                "  <Voice>\n" +
                "    <MediaId><![CDATA[{3}]]></MediaId>\n" +
                "  </Voice>\n" +
                "</xml>";
        String timestamp = DateUtil.getTimestampStr();
        String msg = MessageFormat.format(template, toUser, fromUser, timestamp, mediaId);
        return msg;
    }

    /**
     * 回复视频消息
     * @param toUser
     * @param fromUser
     * @param video
     * @return
     */
    public static String replyVideo(String toUser, String fromUser, Video video) {
        String template = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[video]]></MsgType>\n" +
                "  <Video>\n" +
                "    <MediaId><![CDATA[{3}]]></MediaId>\n" +
                "    <Title><![CDATA[{4}]]></Title>\n" +
                "    <Description><![CDATA[{5}]]></Description>\n" +
                "  </Video>\n" +
                "</xml>";
        String timestamp = DateUtil.getTimestampStr();
        String msg = MessageFormat.format(template, toUser, fromUser, timestamp, video.getMediaId(), video.getTitle(), video.getDescription());
        return msg;
    }

    /**
     * 回复音乐消息
     * @param toUser
     * @param fromUser
     * @param music
     * @return
     */
    public static String replyMusic(String toUser, String fromUser, Music music) {
        String template = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[music]]></MsgType>\n" +
                "  <Music>\n" +
                "    <Title><![CDATA[{3}]]></Title>\n" +
                "    <Description><![CDATA[{4}]]></Description>\n" +
                "    <MusicUrl><![CDATA[{5}]]></MusicUrl>\n" +
                "    <HQMusicUrl><![CDATA[{6}]]></HQMusicUrl>\n" +
                "    <ThumbMediaId><![CDATA[{7}]]></ThumbMediaId>\n" +
                "  </Music>\n" +
                "</xml>";
        String timestamp = DateUtil.getTimestampStr();
        String msg = MessageFormat.format(template, toUser, fromUser, timestamp, music.getTitle(), music.getDescription(), music.getHqMusicUrl(), music.getThumbMediaId());
        return msg;
    }

    /**
     * 回复图文消息
     * @param toUser
     * @param fromUser
     * @param articles
     * @return
     */
    public static String replayArticles(String toUser, String fromUser, List<Article> articles) {
        String templateFront = "<xml>\n" +
                "  <ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "  <CreateTime>{2}</CreateTime>\n" +
                "  <MsgType><![CDATA[news]]></MsgType>\n" +
                "  <ArticleCount>{3}</ArticleCount>\n" +
                "  <Articles>\n";
        String timestamp = DateUtil.getTimestampStr();
        int size = articles.size();
        StringBuilder msg = new StringBuilder(MessageFormat.format(templateFront, toUser, fromUser, timestamp, size));

        String itemTemplate = "<item>\n" +
                "      <Title><![CDATA[{0}]]></Title>\n" +
                "      <Description><![CDATA[{1}]]></Description>\n" +
                "      <PicUrl><![CDATA[{2}]]></PicUrl>\n" +
                "      <Url><![CDATA[{3}]]></Url>\n" +
                "    </item>";
        for (Article article : articles) {
            String item = MessageFormat.format(itemTemplate, article.getTitle(), article.getDescription(), article.getPicUrl(), article.getUrl());
            msg.append(item);
        }
        msg.append("</Articles>\n" + "</xml>");
        return msg.toString();
    }


}
