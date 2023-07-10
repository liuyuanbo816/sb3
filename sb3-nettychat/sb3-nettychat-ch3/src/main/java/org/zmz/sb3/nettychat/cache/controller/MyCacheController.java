package org.zmz.sb3.nettychat.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.nettychat.cache.Cache;
import org.zmz.sb3.nettychat.cache.MyCache;

@RestController
@RequestMapping("/mycache")
public class MyCacheController {

    @Autowired
    @Qualifier("mapCache")
    Cache cache;

    @GetMapping("/index")
    @MyCache("你好啊")
    public String index(String id) {
        return id + " Index";
    }

    @GetMapping("/index2")
    public String index2(String id) {
        return id + " Index2";
    }

    @GetMapping("/index3")
    public String index3() {
        return cache.simpleCache();
    }

}
