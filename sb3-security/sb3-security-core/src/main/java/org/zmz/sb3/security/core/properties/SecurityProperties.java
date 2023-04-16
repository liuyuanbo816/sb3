package org.zmz.sb3.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sb3.security")
public class SecurityProperties {
    private BrowserSecurityProperties browser = new BrowserSecurityProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserSecurityProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserSecurityProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
