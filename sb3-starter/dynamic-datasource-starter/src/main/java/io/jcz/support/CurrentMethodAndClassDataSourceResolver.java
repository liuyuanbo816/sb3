package io.jcz.support;

import io.jcz.annotation.DDS;
import org.springframework.util.StringUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * 当前类及方法上面查找注解
 */
public class CurrentMethodAndClassDataSourceResolver extends AbstractDataSourceClassResolver {


    @Override
    public String findKey(Method method, Object targetObject) {
        //获取目标类
        Class<?> targetObjectClass = targetObject.getClass();

        MethodClassKey methodClassKey = new MethodClassKey(method, targetObjectClass);
        String ddsValue = getDDSFromCache(methodClassKey);
        if (StringUtils.hasText(ddsValue)) {
            return ddsValue;
        }
        ddsValue = findDataSourceAttribute(method, DDS.class);
        if (StringUtils.hasText(ddsValue)) {
            putDDSCache(methodClassKey, ddsValue);
            return ddsValue;
        }

        //如果当前方法上面没有注解，就获取用户自定义的类
        Class<?> userClass = ClassUtils.getUserClass(targetObjectClass);
        ddsValue = findDataSourceAttribute(userClass, DDS.class);
        if (StringUtils.hasText(ddsValue) && ClassUtils.isUserLevelMethod(method)) {
            putDDSCache(methodClassKey, ddsValue);
            return ddsValue;
        }
        return null;
    }
}
