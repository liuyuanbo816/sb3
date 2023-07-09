package org.zmz.sb3.nettychat.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.nettychat.web.model.Course;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class CrawlController {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    @RequestMapping("/crawl/mock")
    public Map<Long, List<Course>> mock() {
        List<Course> courses = List.of(
                new Course("Vue2.5开发去哪儿网App", random.nextInt(27, 100)),
                new Course("React 16.4开发简书项目", random.nextInt(27, 100)),
                new Course("React 服务器渲染实战", random.nextInt(27, 100)),
                new Course("手把手掌握解析Webpack4.0", random.nextInt(27, 100))
        );
        long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return Map.of(timestamp, courses);
    }

}
