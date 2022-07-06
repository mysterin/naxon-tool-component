package com.naxon.tool.wechat.constant;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/24 15:02
 */
public enum MsgType {
    /**
     * 文本
     */
    TEXT("text"),
    /**
     * 图片
     */
    IMAGE("image"),
    /**
     * 语音
     */
    VOICE("voice"),
    /**
     * 视频
     */
    VIDEO("video"),
    /**
     * 音乐
     */
    MUSIC("music"),
    /**
     * 图文
     */
    NEWS("news"),
    /**
     * 短视频
     */
    SHORTVIDEO("shortvideo"),
    /**
     * 地理位置
     */
    LOCATION("location"),
    /**
     * 链接
     */
    LINK("link");

    private String type;

    MsgType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
