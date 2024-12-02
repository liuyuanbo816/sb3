package io.jcz.support;


import io.jcz.annotation.DDS;
import org.springframework.util.StringUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 在接口以及接口的方法上面查找
 */
public class MethodAndInterfaceDataSourceResolver extends AbstractDataSourceClassResolver {
    @Override
    public String findKey(Method method, Object targetObject) {
        Class<?> targetObjectClass = targetObject.getClass();
        MethodClassKey methodClassKey = new MethodClassKey(method, targetObjectClass);
        String ddsCache = getDDSFromCache(methodClassKey);
        if (StringUtils.hasText(ddsCache)) {
            return ddsCache;
        }

        Class<?> userClass = ClassUtils.getUserClass(targetObjectClass);

        for (Class<?> interfaceClazz : ClassUtils.getAllInterfacesForClassAsSet(userClass)) {
            ddsCache = findDataSourceAttribute(interfaceClazz, DDS.class);
            if (StringUtils.hasText(ddsCache)) {
                putDDSCache(methodClassKey, ddsCache);
                return ddsCache;
            }
            //如果接口上面找不到，就去接口上面的方法查找
            Method specificMethod = ReflectionUtils.findMethod(interfaceClazz, method.getName(), method.getParameterTypes());
            ddsCache = findDataSourceAttribute(specificMethod, DDS.class);
            if (StringUtils.hasText(ddsCache)) {
                putDDSCache(methodClassKey, ddsCache);
                return ddsCache;
            }
        }
        return null;
    }
}
