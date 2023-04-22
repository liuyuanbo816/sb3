package org.zmz.sb3.security.browser.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.zmz.sb3.security.browser.handler.MyAuthenticationFailureHandler;
import org.zmz.sb3.security.browser.handler.MyAuthenticationSuccessHandler;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.SmsCodeFilter;
import org.zmz.sb3.security.core.validate.code.ValidateCodeFilter;
import org.zmz.sb3.security.core.validate.code.mobile.SmsCodeAuthenticationSecurityConfig;

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

    @Resource
    private UserDetailsService myUserDetailsServiceImpl;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

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

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        return httpSecurity
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(myUserDetailsServiceImpl)
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeTokenExpireIn())
                .and()
                .formLogin()
                    .loginPage("/authentication/required")
                    .loginProcessingUrl("/authentication/form")
                    .usernameParameter("uname").passwordParameter("pwd")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/error", "/authentication/required", "/code/*", "/favicon.ico", securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().requestCache(cache -> cache.requestCache(requestCache))
                .apply(smsCodeAuthenticationSecurityConfig)
                .and().build();
    }

}
