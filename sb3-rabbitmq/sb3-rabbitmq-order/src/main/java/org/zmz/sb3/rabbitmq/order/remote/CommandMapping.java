package org.zmz.sb3.rabbitmq.order.remote;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandMapping {
    String url() default "";

    String wealthUrl() default "";
}
