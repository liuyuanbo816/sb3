package org.zjc.smalltools.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjc.smalltools.common.R;
import org.zjc.smalltools.vo.response.EnvInfo;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/envInfo")
public class EnvInfoController {

    @Resource
    ObjectMapper objectMapper;

    @GetMapping("/initConfig")
    public R<List<EnvInfo>> initEnvInfoConfig() {
        TypeReference<List<EnvInfo>> typeReference = new TypeReference<>() {
        };
        try {
            List<EnvInfo> envInfos = objectMapper.readValue(new ClassPathResource("envInfoInit.json").getInputStream(), typeReference);
            return R.ok(envInfos);
        } catch (IOException e) {
            throw new RuntimeException("初始化环境信息 发生 IO 异常 " + e.getMessage());
        }
    }

}
