package org.zmz.sb3.security.examples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ExampleWebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().permitAll()
                .and()
                .csrf().disable();
        return httpSecurity.build();
    }

}
