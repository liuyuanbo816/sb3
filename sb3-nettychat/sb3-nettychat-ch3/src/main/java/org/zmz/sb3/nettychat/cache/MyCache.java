package org.zmz.sb3.nettychat.cache;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCache {
    String value() default "";
}
