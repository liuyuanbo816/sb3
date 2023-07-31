package org.zmz.sb3.vertx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.vertx.common.R;
import org.zmz.sb3.vertx.utils.DbUtil;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserId")
    public R<Long> getUserId() {
        long userId = DbUtil.getUserId();
        return R.ok(userId);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello Spring Security Greeting";
    }

}
