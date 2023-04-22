package org.zmz.sb3.security.core.validate.code;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.ValidateCodeProcessor;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    private SecurityProperties securityProperties;
    private static final Set<String> urls = new HashSet<>();
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    public void setMyAuthenticationFailureHandler(AuthenticationFailureHandler myAuthenticationFailureHandler) {
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String url = securityProperties.getCode().getSms().getUrl();
        String[] configUrls = StringUtils.tokenizeToStringArray(url, ",");
        Collections.addAll(urls, configUrls);
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        String requestURI = request.getRequestURI();
        for (String url : urls) {
            if (pathMatcher.match(url, requestURI)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            //进入校验逻辑
            try {
                validate(request);
            } catch (ValidateCodeException e) {
                myAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getSession();
        String sessionKey = ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS";
        ValidateCode codeInSession = (ValidateCode) session.getAttribute(sessionKey);
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "smsCode");

        if (!StringUtils.hasLength(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!codeInRequest.equals(codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        session.removeAttribute(sessionKey);
    }
}
