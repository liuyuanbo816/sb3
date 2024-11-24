package io.jcz.autoconfigure;


import io.jcz.support.CurrentMethodAndClassDataSourceResolver;
import io.jcz.support.DataSourceClassResolver;
import io.jcz.support.MethodAndInterfaceDataSourceResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DynamicDataSourceClassResolverAutoConfiguration {

    /**
     * 解析数据源列表
     */
    @Bean
    public List<DataSourceClassResolver> dataSourceClassResolvers() {
        List<DataSourceClassResolver> resolvers = new ArrayList<>();
        resolvers.add(new CurrentMethodAndClassDataSourceResolver());
        resolvers.add(new MethodAndInterfaceDataSourceResolver());
        return resolvers;
    }
}
