package zzjjcc.config.tx;

import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import zzjjcc.config.advisor.DynamicDataSourceAdvisor;
import zzjjcc.config.anno.DDSTransactional;
import zzjjcc.interceptor.DynamicTransactionalInterceptor;

@Configuration
public class MultipleDataSourceTxConfig {
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    public Advisor dynamicTransactionDataSourceAnnocationAdvisor() {
        DynamicTransactionalInterceptor advice = new DynamicTransactionalInterceptor();
        return new DynamicDataSourceAdvisor(advice, DDSTransactional.class);
    }
}
