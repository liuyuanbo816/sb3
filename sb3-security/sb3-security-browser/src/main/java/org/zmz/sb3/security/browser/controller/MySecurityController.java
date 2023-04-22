package org.zmz.sb3.security.browser.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.security.browser.response.SimpleResponse;
import org.zmz.sb3.security.core.properties.SecurityProperties;

import java.io.IOException;

import static org.zmz.sb3.security.core.properties.SecurityConstants.DEFAULT_REQUIRE_AUTHENTICATION_URL;

@RestController
public class MySecurityController {

    private static final Logger LOG = LoggerFactory.getLogger(MySecurityController.class);

    private static final RequestCache requestCache = new HttpSessionRequestCache();
    private static final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    SecurityProperties securityProperties;

    @RequestMapping(DEFAULT_REQUIRE_AUTHENTICATION_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse authenticationRequired(HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            LOG.info("引发跳转的请求Url: {}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,
                        securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证,请引导用户到登录页");
    }

}
