package org.zmz.sb3.nettychat.cache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
@Slf4j
public class MyCacheAspect {

    @Pointcut("@annotation(org.zmz.sb3.nettychat.cache.MyCache) ")
    public void pointCut() {

    }

    @Around("pointCut() && @annotation(myCache)")
    public Object action(ProceedingJoinPoint joinPoint, MyCache myCache) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String value = myCache.value();

        log.info("=======开始进入自定义切面======= : 缓存value {}", value);
        try {
            log.info("=======返回通知=======");
            Object result = joinPoint.proceed();
            return result;
        } finally {
            log.info("=======后置通知=======");
        }
    }

}
