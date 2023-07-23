package org.zmz.sb3.newfeatures.minispring.beanfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TestCycleDependency {

    private static final Map<Class<?>, Object> map1 = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Object> map2 = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        TestCycleDependency testCycleDependency = new TestCycleDependency();
        A a = testCycleDependency.getBean(A.class);
        B b = testCycleDependency.getBean(B.class);
        C c = testCycleDependency.getBean(C.class);
        log.info("{}", a);
        log.info("{}", a.b);
        log.info("==============================");
        log.info("{}", b);
        log.info("{}", b.c);
        log.info("==============================");
        log.info("{}", c);
        log.info("{}", c.a);
    }

    public <T> T getBean(Class<T> clz) throws Exception {
        Object bean = map1.get(clz);
        if (bean == null) bean = map2.get(clz);

        if (bean == null) {
            bean = clz.getDeclaredConstructor().newInstance();
            map1.put(clz, bean);
            for (Field field : clz.getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    field.setAccessible(true);
                    Class<?> dependencyType = field.getType();
                    Object dependencyObj = getBean(dependencyType);
                    if (dependencyObj == null)
                        throw new NoSuchBeanDefinitionException("找不到 " + dependencyType.getName() + " 类型的Bean");
                    field.set(bean, dependencyObj);
                }
            }
            //map1.put(clz, bean);
        }
        return (T) bean;
    }

    static class A {
        //@MyAutowired
        B b;
    }

    static class B {
        @MyAutowired
        C c;
    }

    static class C {
        @MyAutowired
        A a;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAutowired {

    }
}
