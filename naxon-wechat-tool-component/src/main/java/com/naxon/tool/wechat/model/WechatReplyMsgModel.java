package com.naxon.tool.wechat.model;

import com.naxon.tool.wechat.WechatMsgUtil;
import com.naxon.tool.wechat.constant.MsgType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/26 14:51
 */
@Data
@Builder
public class WechatReplyMsgModel {
    private String toUserName;
    private String fromUserName;
    private MsgType msgType;
    private String content;
    private String mediaId;
    private Video video;
    private Music music;
    private List<Article> articles;

    /**
     * 回复微信的 xml 消息
     * @return
     */
    public String replyXmlMsg() {
        switch (msgType) {
            case TEXT:
                return WechatMsgUtil.replyText(toUserName, fromUserName, content);
            case IMAGE:
                return WechatMsgUtil.replyImage(toUserName, fromUserName, mediaId);
            case VOICE:
                return WechatMsgUtil.replyVoice(toUserName, fromUserName, mediaId);
            case VIDEO:
                return WechatMsgUtil.replyVideo(toUserName, fromUserName, video);
            case MUSIC:
                return WechatMsgUtil.replyMusic(toUserName, fromUserName, music);
            case NEWS:
                return WechatMsgUtil.replayArticles(toUserName, fromUserName, articles);
            default:
                throw new RuntimeException("未知消息类型，msgType=" + msgType);
        }
    }
}
