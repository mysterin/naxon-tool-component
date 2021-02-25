package com.naxon.tool.common;

import lombok.Data;

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
public class CacheUtil {

    private static Map<String, Node> map = new ConcurrentHashMap<>();

    static {
        recycle();
    }

    /**
     * 回收过期元素
     */
    public static void recycle() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            while (true) {
                Iterator<Map.Entry<String, Node>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Node> entry = iterator.next();
                    Node node = entry.getValue();
                    // 删除已过期元素
                    if (isExpire(node)) {
                        iterator.remove();
                    }
                }
                try {
                    // 每十分钟回收
                    Thread.sleep(10 * 60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException("缓存回收线程中断");
                }
            }
        });
    }

    /**
     * 设置缓存值
     * @param key
     * @param object
     * @param ttl
     * @param <T>
     */
    public static <T> void set(String key, T object, Integer ttl) {
        if (ttl == null) {
            throw new RuntimeException("ttl 不能为空");
        }
        Node<T> node = new Node<>(object, ttl);
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
        return (T) node.getObject();
    }

    /**
     * 是否过期
     * @param node
     * @return
     */
    public static Boolean isExpire(Node node) {
        if (node == null) {
            return true;
        }
        LocalDateTime createtime = node.getCreatetime();
        Integer ttl = node.getTtl();
        LocalDateTime now = DateUtil.getNow();
        // 过期元素
        if (DateUtil.diffSecond(now, createtime) > ttl) {
            return true;
        }
        return false;
    }

    @Data
    public static class Node<T> {
        private T object;
        private LocalDateTime createtime;
        private Integer ttl;

        public Node(T object, Integer ttl) {
            this.object = object;
            this.createtime = DateUtil.getNow();
            this.ttl = ttl;
        }
    }
}
