package io.jcz.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DDS {
    String value();
}
