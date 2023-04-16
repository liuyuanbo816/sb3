package org.zmz.sb3.security.browser.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.zmz.sb3.security.browser.handler.MyAuthenticationFailureHandler;
import org.zmz.sb3.security.browser.handler.MyAuthenticationSuccessHandler;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.ValidateCodeFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig {

    @Autowired
    SecurityProperties securityProperties;

    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private DataSource dataSource;

    private UserDetailsService myUserDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository
                = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        httpSecurity
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(myUserDetailsServiceImpl)
                    .tokenValiditySeconds(securityProperties.getRememberMeTokenExpireIn())
                .and()
                .formLogin()
                    .loginPage("/authentication/required")
                    .loginProcessingUrl("/authentication/form")
                    .usernameParameter("uname").passwordParameter("pwd")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/error", "/authentication/required", "/code/image", "/favicon.ico", securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().requestCache(cache -> cache.requestCache(requestCache));
        return httpSecurity.build();
    }

}
