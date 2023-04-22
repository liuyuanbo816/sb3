package org.zmz.sb3.security.core.authentication;

import jakarta.annotation.Resource;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zmz.sb3.security.core.properties.SecurityConstants;

/**
 * 表单登录配置
 */
public class FormAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    public void formAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_REQUIRE_AUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .usernameParameter("uname").passwordParameter("pwd")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
    }

}