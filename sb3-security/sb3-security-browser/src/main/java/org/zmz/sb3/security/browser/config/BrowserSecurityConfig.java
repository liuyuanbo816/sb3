package org.zmz.sb3.security.browser.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.zmz.sb3.security.core.authentication.FormAuthenticationConfig;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.ValidateCodeSecurityConfig;
import org.zmz.sb3.security.core.validate.code.mobile.SmsCodeAuthenticationSecurityConfig;

import javax.sql.DataSource;

import static org.zmz.sb3.security.core.properties.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends FormAuthenticationConfig {

    @Autowired
    SecurityProperties securityProperties;

    @Resource
    private DataSource dataSource;

    @Resource
    private UserDetailsService myUserDetailsServiceImpl;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource
    ValidateCodeSecurityConfig validateCodeSecurityConfig;

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

        formAuthenticationConfig(httpSecurity);
        return httpSecurity.apply(validateCodeSecurityConfig)
                .and().apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(myUserDetailsServiceImpl)
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeTokenExpireIn())
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(
                            DEFAULT_TOMCAT_ERROR_PATH,
                            DEFAULT_REQUIRE_AUTHENTICATION_URL,
                            DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                            DEFAULT_FAVICON_REQUEST_PATH,
                            securityProperties.getBrowser().getLoginPage()
                    )
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .requestCache(cache -> cache.requestCache(requestCache))
                .build();
    }

}
