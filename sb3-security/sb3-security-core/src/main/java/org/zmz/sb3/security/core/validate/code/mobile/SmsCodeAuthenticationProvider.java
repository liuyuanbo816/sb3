package org.zmz.sb3.security.core.validate.code.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof SmsCodeAuthenticationToken smsCodeAuthenticationToken) {
            UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());
            if (userDetails == null)
                throw new InternalAuthenticationServiceException("无法获取用户信息");
            SmsCodeAuthenticationToken smsCodeAuthenticationTokenResult
                    = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
            smsCodeAuthenticationTokenResult.setDetails(smsCodeAuthenticationToken.getDetails());
            return smsCodeAuthenticationTokenResult;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
