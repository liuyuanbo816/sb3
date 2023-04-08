package org.zmz.sb3.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .loginPage("/my-login.html")
                .loginProcessingUrl("/authentication/form")
                .usernameParameter("uname").passwordParameter("pass")
                .and()
                .authorizeHttpRequests().requestMatchers("/my-login.html").permitAll()
                .and()
                .csrf().disable();
        return httpSecurity.build();
    }

}
