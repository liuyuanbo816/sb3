package org.zmz.sb3.nettychat.demo.a;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SignaturePropInit {

    @Resource
    SignatureProp signatureProp;

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>> {}", signatureProp);
    }

}
