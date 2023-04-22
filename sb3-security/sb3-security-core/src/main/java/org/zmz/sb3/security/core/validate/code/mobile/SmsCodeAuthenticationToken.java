package org.zmz.sb3.security.core.validate.code.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    public SmsCodeAuthenticationToken(Object mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Object mobile,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = mobile;
        super.setAuthenticated(true); // must use super, as we override
    }

    public static SmsCodeAuthenticationToken unauthenticated(Object principal) {
        return new SmsCodeAuthenticationToken(principal);
    }

    public static SmsCodeAuthenticationToken authenticated(Object principal,
                                                           Collection<? extends GrantedAuthority> authorities) {
        return new SmsCodeAuthenticationToken(principal, authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
