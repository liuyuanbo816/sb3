package org.zmz.sb3.newfeatures.minispring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/register")
    public String register(@RequestParam String reqNo, @RequestParam String type) {
        eventService.register(reqNo, type);
        return "注册成功";
    }

}
