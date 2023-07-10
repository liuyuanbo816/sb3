package org.zmz.sb3.nettychat.cache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

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
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        doParse(args, method);
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

    private void doParse(Object[] args, Method method) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        StandardReflectionParameterNameDiscoverer u = new StandardReflectionParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
    }

}
