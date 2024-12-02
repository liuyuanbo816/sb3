package io.jcz.autoconfigure;


import com.alibaba.druid.pool.DruidDataSource;
import io.jcz.creator.DataSourceCreator;
import io.jcz.creator.DefaultDataSourceCreator;
import io.jcz.creator.DruidDataSourceCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class DynamicDataSourceCreatorAutoConfiguration {
    public static final int DRUID_ORDER = 500;

    @Bean
    public DefaultDataSourceCreator defaultDataSourceCreator(List<DataSourceCreator> creators) {
        return new DefaultDataSourceCreator(creators);
    }

    @ConditionalOnClass(DruidDataSource.class)
    @Configuration
    static class DruidDataSourceCreatorConfiguration {
        @Order(DRUID_ORDER)
        @Bean
        public DruidDataSourceCreator dataSourceCreator() {
            return new DruidDataSourceCreator();
        }
    }
}
