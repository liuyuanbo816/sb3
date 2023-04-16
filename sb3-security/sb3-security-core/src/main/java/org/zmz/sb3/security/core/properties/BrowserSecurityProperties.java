package org.zmz.sb3.security.core.properties;

public class BrowserSecurityProperties {
    private String loginPage = "/my-login.html";
    //默认 JSON
    private LoginType loginType = LoginType.JSON;

    private int rememberMeTokenExpireIn = 3600;

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

    public int getRememberMeTokenExpireIn() {
        return rememberMeTokenExpireIn;
    }

    public void setRememberMeTokenExpireIn(int rememberMeTokenExpireIn) {
        this.rememberMeTokenExpireIn = rememberMeTokenExpireIn;
    }
}
