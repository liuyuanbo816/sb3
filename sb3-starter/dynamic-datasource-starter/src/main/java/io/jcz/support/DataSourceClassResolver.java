package io.jcz.support;


import java.lang.reflect.Method;

/**
 * 数据源解析器
 */
public interface DataSourceClassResolver {

    /**
     * 查找数据源名称
     */
    String findKey(Method method, Object targetObject);

}
