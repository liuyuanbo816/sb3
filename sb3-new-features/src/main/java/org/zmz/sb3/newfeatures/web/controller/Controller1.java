package org.zmz.sb3.newfeatures.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("c1")
@Slf4j
public class Controller1 {
    @GetMapping("/c1")
    public String c1() {
        log.debug("c-1");
        return "c1";
    }

    @GetMapping("/c2")
    public String c2(String ip) {
        log.debug("c-2");
        return ip;
    }
}
