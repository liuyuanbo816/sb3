package io.jcz.interceptor;

import io.jcz.support.DataSourceClassResolver;
import io.jcz.util.DynamicDataSourceContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 当在方法或者类级别切 到了 @DDS 注解,就会被当前拦截器拦截
 */
public class DynamicDataSourceInterceptor implements MethodInterceptor {

    private final List<DataSourceClassResolver> dataSourceClassResolvers;

    public DynamicDataSourceInterceptor(List<DataSourceClassResolver> dataSourceClassResolvers) {
        this.dataSourceClassResolvers = dataSourceClassResolvers;
    }

    @Override
    public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
        String dsKey = determineDataSourceKey(invocation);
        try {
            DynamicDataSourceContextHolder.addDataSource(dsKey);
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeCurrentDataSource();
        }
    }

    private String determineDataSourceKey(MethodInvocation invocation) {
        // 通过当前方法寻找多数据源注解 @DDS
        for (DataSourceClassResolver resolver : this.dataSourceClassResolvers) {
            String key = resolver.findKey(invocation.getMethod(), invocation.getThis());
            if (StringUtils.hasText(key)) {
                return key;
            }
        }
        return "";
    }

}
