package com.naxon.tool.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author xiaobin.lin
 * @Description
 * @createTime 2020-09-07 17:59:46
 */
@Slf4j
class JsonUtilsTest {

    @Test
    void parseJson() {
        String text = "{\"id\": \"123456789\", \"name\": \"naxon\", \"age\": 28, \"sex\": \"male\"}";
        JSONObject json = JsonUtils.parseJson(text);
        assertEquals("123456789", json.getString("id"));
        assertEquals("naxon", json.getString("name"));
        assertEquals(28, json.getInteger("age"));
        assertEquals("male", json.getString("sex"));
    }

    @Test
    void testParseJson() {
        String text = "{\"id\": \"123456789\", \"name\": \"naxon\", \"age\": 28, \"sex\": \"male\"}";
        User user = JsonUtils.parseJson(text, User.class);
        assertEquals("123456789", user.getId());
        assertEquals("naxon", user.getName());
        assertEquals(28, user.getAge());
        assertEquals("male", user.getSex());
    }

    @Test
    void parseJsonToList() {
        String text = "[{\"id\": \"13579\", \"name\": \"naxon\", \"age\": 28, \"sex\": \"male\"}, " +
                "{\"id\": \"24680\", \"name\": \"mysterin\", \"age\": 30, \"sex\": \"female\"}]";
        List<User> users = JsonUtils.parseJsonToList(text, User.class);
        assertEquals(2, users.size());
        assertEquals("13579", users.get(0).getId());
        assertEquals("24680", users.get(1).getId());
    }

    @Test
    void toJsonString() {
        User user = new User()
                .setId("13579")
                .setName("naxon")
                .setAge(28)
                .setSex("male");
        String s = JsonUtils.toJsonString(user);
        log.debug(s);
    }

    @Data
    @Accessors(chain = true)
    public static class User {
        private String id;
        private String name;
        private Integer age;
        private String sex;
    }
}