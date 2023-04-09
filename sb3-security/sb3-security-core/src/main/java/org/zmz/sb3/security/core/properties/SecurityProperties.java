package org.zmz.sb3.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sb3.security")
public class SecurityProperties {
    private BrowserSecurityProperties browser = new BrowserSecurityProperties();

    public BrowserSecurityProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserSecurityProperties browser) {
        this.browser = browser;
    }
}
