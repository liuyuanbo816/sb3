package io.jcz.support;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractDataSourceClassResolver implements DataSourceClassResolver {

    private final Map<Object, String> DDS_CACHE = new ConcurrentHashMap<>();

    /**
     * 查找对应节点上的注解信息
     */
    protected String findDataSourceAttribute(AnnotatedElement element, Class<? extends Annotation> annotation) {
        AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(element, annotation);
        if (attributes != null) {
            return attributes.getString("value");
        }
        return null;
    }

    protected String getDDSFromCache(Object cacheKey) {
        return DDS_CACHE.get(cacheKey);
    }

    protected void putDDSCache(Object cacheKey, String ddsValue) {
        this.DDS_CACHE.put(cacheKey, ddsValue);
    }
}
