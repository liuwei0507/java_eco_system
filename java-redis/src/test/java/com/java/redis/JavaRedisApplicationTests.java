package com.java.redis;

import com.java.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class JavaRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "测试名字");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);


        User user = User.builder().name("测试").age(10).build();
        redisTemplate.opsForValue().set("user:1", user);

        Object user_value = redisTemplate.opsForValue().get("user:1");
        System.out.println("user=" + user_value);


    }

}
