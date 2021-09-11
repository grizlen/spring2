package ru.geekbrains.market.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void setKey(String key) {
        if (key.startsWith("Bearer ")) {
            key = key.replace("Bearer ", "");
        }
        redisTemplate.opsForValue().set(key, "abc", Duration.ofHours(1));
//        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
    }

//    public String redisGet(String key) {
//        String  result = redisTemplate.opsForValue().get(key);
//        return result;
//    }

    public boolean hasKey(String key) {
        if (key.startsWith("Bearer ")) {
            key = key.replace("Bearer ", "");
        }
        return redisTemplate.hasKey(key);
    }
}
