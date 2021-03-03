package com.naxon.tool.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/3/3 11:06
 */
@Slf4j
public class CacheUtilTest {

    @Test
    public void getset() throws InterruptedException {
        String key = "C_U_T_301";
        long t = System.currentTimeMillis();
        CacheUtil.set(key, t, 30);
        Long tt = CacheUtil.get(key);
        log.debug("tt={}", tt);
        Thread.sleep(31000);

        Long ttt = CacheUtil.get(key);
        log.debug("ttt={}", ttt);
        Thread.sleep(2*60000);
    }

}