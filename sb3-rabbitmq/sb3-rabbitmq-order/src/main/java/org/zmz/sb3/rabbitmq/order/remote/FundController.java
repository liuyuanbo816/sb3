package org.zmz.sb3.rabbitmq.order.remote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundController {

    @GetMapping("/funds")
    public String funds() {
        return "list";
    }

}
