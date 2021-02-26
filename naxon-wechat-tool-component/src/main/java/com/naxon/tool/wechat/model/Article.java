package com.naxon.tool.wechat.model;

import lombok.Data;

/**
 * @author linxiaobin
 * @Description 图文消息
 * @date 2021/2/26 18:19
 */
@Data
public class Article {
    private String title;
    private String description;
    private String picUrl;
    private String url;
}
