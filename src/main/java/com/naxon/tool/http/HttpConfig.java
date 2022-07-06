package com.naxon.tool.http;

import lombok.Data;

/**
 * http 请求配置
 */
@Data
public class HttpConfig {
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
