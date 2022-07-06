package com.naxon.tool.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/25 11:10
 */
@Slf4j
public class CacheUtil {

    private static Map<String, Node> map = new ConcurrentHashMap<>();
    private static final Long INTERNAL = 60000L;

    static {
        recycle();
    }

    /**
     * 回收过期元素
     */
    private static void recycle() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            log.info("缓存回收线程启动");
            boolean start = true;
            while (start) {
                Iterator<Map.Entry<String, Node>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Node> entry = iterator.next();
                    String key = entry.getKey();
                    Node node = entry.getValue();
                    // 删除已过期元素
                    if (isExpire(node)) {
                        log.debug("回收缓存，key={}，node={}", key, node);
                        iterator.remove();
                    }
                }
                try {
                    // 每分钟回收
                    Thread.sleep(INTERNAL);
                } catch (InterruptedException e) {
                    log.error("缓存回收线程中断", e);
                    start = false;
                }
            }
            log.info("缓存回收线程结束");
        });
    }

    /**
     * 设置缓存值
     * @param key
     * @param value
     * @param ttl 生存时间，单位秒
     * @param <T>
     */
    public static <T> void set(String key, T value, Integer ttl) {
        if (ttl == null) {
            throw new RuntimeException("ttl 不能为空");
        }
        Node<T> node = new Node<>(value, ttl);
        map.put(key, node);
    }

    /**
     * 获取元素
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T get(String key) {
        Node node = map.get(key);
        if (isExpire(node)) {
            return null;
        }
        return (T) node.getValue();
    }

    /**
     * 是否过期
     * @param node
     * @return
     */
    private static Boolean isExpire(Node node) {
        if (node == null) {
            return true;
        }
        LocalDateTime createtime = node.createtime;
        Integer ttl = node.ttl;
        LocalDateTime now = DateUtil.getNow();
        // 过期元素
        if (DateUtil.diffSecond(now, createtime) > ttl) {
            return true;
        }
        return false;
    }

    @Data
    public static class Node<T> {
        private T value;
        private LocalDateTime createtime;
        private Integer ttl;

        public Node(T value, Integer ttl) {
            this.value = value;
            this.createtime = DateUtil.getNow();
            this.ttl = ttl;
        }
    }
}
