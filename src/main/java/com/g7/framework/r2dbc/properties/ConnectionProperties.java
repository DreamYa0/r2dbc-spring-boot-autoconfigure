package com.g7.framework.r2dbc.properties;

import org.springframework.validation.annotation.Validated;

@Validated
public class ConnectionProperties {

    /**
     * 数据源地址
     */
    private String url;
    /**
     * 数据源账号
     */
    private String username;
    /**
     * 数据源密码
     */
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
