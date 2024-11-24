package io.jcz.autoconfigure;


import io.jcz.advisor.DynamicDataSourceAdvisor;
import io.jcz.annotation.DDS;
import io.jcz.annotation.DDSTransactional;
import io.jcz.datasource.DynamicRoutingDataSource;
import io.jcz.init.DataSourceInit;
import io.jcz.init.DecryptDataSourceInit;
import io.jcz.interceptor.DynamicDataSourceInterceptor;
import io.jcz.interceptor.DynamicTransactionalInterceptor;
import io.jcz.propterties.DynamicDataSourceProperty;
import io.jcz.provider.DynamicDataSourceProvider;
import io.jcz.provider.YamlDynamicDataSourceProvider;
import io.jcz.support.DataSourceClassResolver;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = DynamicDataSourceProperty.class)
@Import(value = {DynamicDataSourceClassResolverAutoConfiguration.class, DynamicDataSourceCreatorAutoConfiguration.class})
@ConditionalOnProperty(prefix = DynamicDataSourceProperty.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class DynamicDataSourceAutoConfiguration {

    private final DynamicDataSourceProperty dataSourceProperty;

    public DynamicDataSourceAutoConfiguration(DynamicDataSourceProperty dataSourceProperty) {
        this.dataSourceProperty = dataSourceProperty;
    }

    @Order(0)
    @Bean
    public YamlDynamicDataSourceProvider yamlDynamicDataSourceProvider() {
        return new YamlDynamicDataSourceProvider(dataSourceProperty.getDs());
    }

    //当前不存在其它datasource才去装配
    @ConditionalOnMissingBean
    @Bean
    @Primary
    public DataSource dataSource(List<DynamicDataSourceProvider> providers) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource(providers);
        dataSource.setPrimary(this.dataSourceProperty.getPrimary());
        return dataSource;
    }

    @Bean
    public DataSourceInit dataSourceInit() {
        return new DecryptDataSourceInit();
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    public Advisor dynamicDataSourceAnnocationAdvisor(List<DataSourceClassResolver> resolvers) {
        DynamicDataSourceInterceptor advice = new DynamicDataSourceInterceptor(resolvers);
        return new DynamicDataSourceAdvisor(advice, DDS.class);
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    public Advisor dynamicTransactionDataSourceAnnocationAdvisor() {
        DynamicTransactionalInterceptor advice = new DynamicTransactionalInterceptor();
        return new DynamicDataSourceAdvisor(advice, DDSTransactional.class);
    }
}
