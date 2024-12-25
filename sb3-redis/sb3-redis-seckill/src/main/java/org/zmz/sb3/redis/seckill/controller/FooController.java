package org.zmz.sb3.redis.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.redis.seckill.common.R;

import java.util.UUID;

@RestController
@RequestMapping("/foo")
public class FooController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/c1")
    public R<String> c1(@RequestParam("userCode") String userCode) {
        stringRedisTemplate.opsForHash().put("mytest", userCode, UUID.randomUUID().toString());
        return R.success(">>>>>> OK >>>>>>");
    }

}
