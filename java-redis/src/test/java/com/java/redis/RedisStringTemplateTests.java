package com.java.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisStringTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testString() throws JsonProcessingException {
        stringRedisTemplate.opsForValue().set("name", "测试名字");

        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);


        User user = User.builder().name("测试").age(10).build();
        String json = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:1", json);

        String user_value = stringRedisTemplate.opsForValue().get("user:1");
        User user1 = objectMapper.readValue(user_value, User.class);
        System.out.println("user=" + user1);
    }

}
