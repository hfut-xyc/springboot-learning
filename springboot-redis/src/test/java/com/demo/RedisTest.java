package com.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testString1() {
        String key = "name";
        String value = "计算机网络";
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);

        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
    }

    @Test
    public void testString2() throws JsonProcessingException {
        String key = "user";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "操作系统");
        String value = mapper.writeValueAsString(map);
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);

        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
    }

    @Test
    public void testHash() {
        String key = "user";
        stringRedisTemplate.opsForHash().put(key, "id", "2");
        stringRedisTemplate.opsForHash().put(key, "name", "计算机网络");

        Object o = stringRedisTemplate.opsForHash().entries(key);
        System.out.println(o);
    }
}
