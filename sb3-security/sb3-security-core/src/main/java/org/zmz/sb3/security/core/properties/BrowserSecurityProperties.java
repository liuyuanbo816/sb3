package org.zmz.sb3.security.core.properties;

public class BrowserSecurityProperties {
    private String loginPage = "/my-login.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
