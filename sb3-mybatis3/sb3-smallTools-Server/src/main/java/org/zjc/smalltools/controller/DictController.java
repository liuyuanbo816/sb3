package org.zjc.smalltools.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjc.smalltools.common.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequestMapping("/dict")
@RestController
public class DictController {

    @Resource
    ObjectMapper objectMapper;

    @PostMapping("/dictKvInitList")
    public R<List<Map<String, String>>> dictKvInitList(@RequestBody Map<String, String> envMap) {
        String env = envMap.get("env");
        String keyNo = envMap.get("keyNo");
        String keyName = envMap.get("keyName");
        try {
            InputStream in = new ClassPathResource(env + "-dictKvInitList").getInputStream();
            TypeReference<List<Map<String, String>>> typeReference = new TypeReference<>() {
            };
            List<Map<String, String>> maps = objectMapper.readValue(in, typeReference);
            List<Map<String, String>> res = maps.stream().filter(e -> {
                String mapKeyNo = e.get("keyNo");
                String mapKeyName = e.get("mapKeyName");
                return mapKeyNo.equals(keyNo) && mapKeyName.equals(keyName);
            }).toList();
            return R.ok(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
