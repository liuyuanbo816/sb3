package org.zmz.sb3.redis.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> void set(String key, long expireTime, T value) {
        redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    public <T> void set(String key, T value) {
        this.set(key, 1800, value);
    }

    public <T> T get(String key, Class<T> clz) {
        Object o = redisTemplate.opsForValue().get(key);
        return clz.cast(o);
    }

}
