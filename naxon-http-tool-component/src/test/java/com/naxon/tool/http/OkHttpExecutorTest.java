package com.naxon.tool.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/3/3 10:42
 */
@Slf4j
public class OkHttpExecutorTest {

    private OkHttpExecutor okHttpExecutor = new OkHttpExecutor();

    @Test
    public void syncGetJson() throws IOException {
        String url = "https://api.bilibili.com/x/relation/stat?vmid=777536&spm_id_from=333.788.b_636f6d6d656e74.111";
        JSONObject json = okHttpExecutor.syncGetJson(url);
        log.debug(json.toJSONString());
    }
}