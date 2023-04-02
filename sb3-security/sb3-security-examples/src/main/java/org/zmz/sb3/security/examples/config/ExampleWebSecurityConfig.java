package org.zmz.sb3.security.examples.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.zmz.sb3.security.examples.filter.InvokeTimeFilter;

import java.util.List;

@Configuration
public class ExampleWebSecurityConfig {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().permitAll()
                .and()
                .csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    public FilterRegistrationBean<Filter> invokeTimeFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        InvokeTimeFilter invokeTimeFilter = new InvokeTimeFilter();
        invokeTimeFilter.setRequestMappingHandlerMapping(requestMappingHandlerMapping);
        filterFilterRegistrationBean.setFilter(invokeTimeFilter);
        filterFilterRegistrationBean.setUrlPatterns(List.of("/*"));
        return filterFilterRegistrationBean;
    }

}
