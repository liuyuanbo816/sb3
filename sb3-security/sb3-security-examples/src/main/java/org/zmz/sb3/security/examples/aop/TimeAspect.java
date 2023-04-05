package org.zmz.sb3.security.examples.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    public static final Logger LOG = LoggerFactory.getLogger(TimeAspect.class);

    @Pointcut("execution(* org.zmz.sb3.security.examples.controller..*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        LOG.info("timeAspect start {} ", System.currentTimeMillis());
        Object result = pjp.proceed();
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        Object target = pjp.getTarget();
        Object thisObj = pjp.getThis();
        SourceLocation sourceLocation = pjp.getSourceLocation();
        JoinPoint.StaticPart staticPart = pjp.getStaticPart();
        LOG.info("timeAspect end {} ", System.currentTimeMillis());
        return result;
    }

}
