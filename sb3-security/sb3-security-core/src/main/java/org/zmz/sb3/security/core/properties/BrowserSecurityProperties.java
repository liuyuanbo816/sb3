package org.zmz.sb3.security.core.properties;

public class BrowserSecurityProperties {
    private String loginPage = "/my-login.html";
    //默认 JSON
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
