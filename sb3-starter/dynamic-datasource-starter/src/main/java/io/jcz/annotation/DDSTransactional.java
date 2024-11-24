package io.jcz.annotation;


import java.lang.annotation.*;

/**
 * 多数据源事务注解
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DDSTransactional {
}
