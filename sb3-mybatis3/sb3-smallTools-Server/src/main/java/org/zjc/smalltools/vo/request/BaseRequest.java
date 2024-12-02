package org.zjc.smalltools.vo.request;

import jakarta.validation.constraints.NotBlank;

public class BaseRequest {
    @NotBlank(message = "数据库账号不能为空")
    private String username;
    @NotBlank(message = "数据库密码不能为空")
    private String password;
    @NotBlank(message = "数据库连接地址不能为空")
    private String jdbcUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
