package org.zmz.sb3.security.examples.filter.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InvokeTimeCalculate {
}
