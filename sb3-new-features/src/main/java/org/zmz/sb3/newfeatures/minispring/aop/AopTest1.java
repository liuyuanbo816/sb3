package org.zmz.sb3.newfeatures.minispring.aop;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class AopTest1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AopTest1.class);
        MyService myService = ctx.getBean(MyService.class);

        log.info("{}", myService.getClass());
        log.info("main: {}", myService.getX());
        log.info("main: {}", myService.x);
    }

    @Service
    static class MyService {
        int x;

        public int getX() {
            log.info("getX()");
            return this.x;
        }

        @PostConstruct
        public void init() {
            this.x = 100;
        }
    }

    @Component
    @Aspect
    static class Aop1 {
        @Before("execution(* getX())")
        public void before() {
            log.info("执行前置切面");
        }
    }
}
