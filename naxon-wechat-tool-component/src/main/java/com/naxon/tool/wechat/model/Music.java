package com.naxon.tool.wechat.model;

import lombok.Data;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/26 18:46
 */
@Data
public class Music {
    private String title;
    private String description;
    private String musicUrl;
    private String hqMusicUrl;
    private String thumbMediaId;
}
