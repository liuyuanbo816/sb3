package org.zmz.sb3.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.zmz.sb3.security.core.properties.SecurityProperties;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig {

    @Autowired
    SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        httpSecurity.formLogin()
                .loginPage("/authentication/required")
                .loginProcessingUrl("/authentication/form")
                .usernameParameter("uname").passwordParameter("pwd")
                .permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().requestCache(cache -> cache.requestCache(requestCache));
        return httpSecurity.build();
    }

}
