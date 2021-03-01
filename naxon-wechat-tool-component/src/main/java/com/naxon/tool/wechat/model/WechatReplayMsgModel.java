package com.naxon.tool.wechat.model;

import com.naxon.tool.wechat.WechatUtil;
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
public class WechatReplayMsgModel {
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
                return WechatUtil.replyText(toUserName, fromUserName, content);
            case IMAGE:
                return WechatUtil.replyImage(toUserName, fromUserName, mediaId);
            case VOICE:
                return WechatUtil.replyVoice(toUserName, fromUserName, mediaId);
            case VIDEO:
                return WechatUtil.replyVideo(toUserName, fromUserName, video);
            case MUSIC:
                return WechatUtil.replyMusic(toUserName, fromUserName, music);
            case NEWS:
                return WechatUtil.replayArticles(toUserName, fromUserName, articles);
            default:
                throw new RuntimeException("未知消息类型，msgType=" + msgType);
        }
    }
}
