package org.zmz.sb3.nettychat.demo.b;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProfilesActiveDemo {

    @Value("${spring.application.name:xxx}")
    private String appName;

    @PostConstruct
    void init() {
        log.info(">>>>>> 当前项目名: {}", appName);
    }

}
