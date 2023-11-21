package org.zmz.sb3.rabbitmq.order.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UspRemoteService {
    @Autowired
    UspApi uspApi;

    public void fundList() {
        TypeReference<UspResponse<List<String>>> typeReference = new TypeReference<>() {
        };
        UspBaseVO uspBaseVO = new UspBaseVO();

        UspResponse<List<String>> response = uspApi.invoke(typeReference, uspBaseVO);
        List<String> data = response.getData();
    }

    @PostConstruct
    public void init() {
        CompletableFuture.runAsync(() -> {
            MDC.put("color", "boldRed");
            log.info("<<<<<<红色日志 , 线程名:{}", Thread.currentThread().getName());
            MDC.clear();
        });

        CompletableFuture.runAsync(() -> {
            MDC.put("color", "boldBlue");
            log.info("线程名:{} ,  蓝色日志>>>>>>", Thread.currentThread().getName());
            MDC.clear();
        });
    }
}
