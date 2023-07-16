package org.zmz.sb3.newfeatures.minispring.beanfactory.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("m_c1")
@Slf4j
public class Controller1 {

    @GetMapping("/miniSpring/c1")
    public String c1() {
        log.debug("mini-spring-1");
        return "c1";
    }

}
