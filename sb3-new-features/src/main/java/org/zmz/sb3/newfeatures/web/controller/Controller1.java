package org.zmz.sb3.newfeatures.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/c1")
public class Controller1 {
    @GetMapping("/level1")
    public String level1() {
        log.debug("level1-1");
        return "level1";
    }

    @GetMapping("/level2")
    public String level2(String ip) {
        log.debug("level-2");
        return ip;
    }

    @PostMapping("/level3")
    public Map<String, Object> level3() {
        log.debug("level-3");
        return Map.of("code", "000000");
    }

    @PostMapping("/level4")
    public Map<String, Object> level4(HttpSession session) {
        Object usercode = session.getAttribute("USERCODE");
        return Map.of("usercode", usercode);
    }
}
