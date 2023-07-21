package org.zmz.sb3.nettychat.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
@Slf4j
public class Demo1Controller {

    @GetMapping("/m1")
    public void m1() {
        int i = 100;
        log.info("m1 初始值: {}", i);
        m2(i);
    }

    public void m2(int i) {
        i += 200;
        log.info("m2 值: {}", i);
        m3(i);
    }

    public void m3(int i) {
        i += 300;
        log.info("m3 值: {}", i);
        m4(i);
    }

    public void m4(int i) {
        i -= 400;
        log.info("m4 值: {}", i);
    }


}
