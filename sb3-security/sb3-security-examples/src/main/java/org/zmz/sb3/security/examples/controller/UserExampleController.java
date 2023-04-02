package org.zmz.sb3.security.examples.controller;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.zmz.sb3.security.examples.common.CommonResponse;
import org.zmz.sb3.security.examples.vo.request.UserPageRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserExampleController {

    public static final Logger LOG = LoggerFactory.getLogger(UserExampleController.class);

    @GetMapping
    public List<String> list() {
        return List.of("AA", "BB", "CC");
    }

    @GetMapping("/mapList")
    public CommonResponse<List<Map<String, ?>>> mapList() {
        List<Map<String, ?>> list = List.of(
                Map.of("k1", "v1"),
                Map.of("k22", "v22"),
                Map.of("k333", "v333"),
                Map.of("k4444", "v4444"),
                Map.of("k55555", "v55555"),
                Map.of("k666666", "v666666")
        );
        return CommonResponse.success(list);
    }

    @GetMapping("/name")
    public List<String> list(@RequestParam(required = false, value = "name", defaultValue = "bobi") String name) {
        LOG.info("{}", name);
        return List.of("AA", "BB", "CC");
    }

    @PostMapping("/page")
    public List<String> page(@RequestBody UserPageRequest userPageRequest) {
        String obj = ReflectionToStringBuilder.toString(userPageRequest, ToStringStyle.MULTI_LINE_STYLE);
        LOG.info("{}", obj);
        return List.of("AA", "BB", "CC");
    }

}
