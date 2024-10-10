package org.zmz.sb3.rabbitmq.order.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonSupport {

    @Autowired
    ObjectMapper objectMapper;

    public <T> String obj2Str(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json 处理错误 ", e);
        }
        return "{}";
    }

    public <T> T str2Obj(String str, Class<T> clz) {
        try {
            return objectMapper.readValue(str, clz);
        } catch (JsonProcessingException e) {
            log.error("json 处理错误 ", e);
        }
        return null;
    }

}
