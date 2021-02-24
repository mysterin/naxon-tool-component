package com.naxon.tool.wechat.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/24 13:41
 */
@Data
public class WechatMsgModel {
    private String msgId;
    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;

    // 文本消息
    private String Content;

    // 素材消息
    private String mediaId;
    private String picUrl;
    private String format;
    private String recognition;
    private String thumbMediaId;

    // 地理位置消息
    private String location_X;
    private String location_Y;
    private String scale;
    private String label;

    // 链接消息；
    private String title;
    private String description;
    private String url;

    // 事件消息
    private String event;
    private String eventKey;
    private String ticket;

    // 上报地理位置事件
    private String latitude;
    private String longitude;
    private String precision;
}
