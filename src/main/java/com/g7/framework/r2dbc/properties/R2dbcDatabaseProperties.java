package com.g7.framework.r2dbc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "spring.datasource")
public class R2dbcDatabaseProperties {

    /**
     * 多数据源公共账号
     */
    private String username;
    /**
     * 多数据源公共密码
     */
    private String password;

    /**
     * 数据源配置
     */
    private Map<String, ConnectionProperties> r2dbc;

    public Map<String, ConnectionProperties> getR2dbc() {
        return r2dbc;
    }

    public void setR2dbc(Map<String, ConnectionProperties> r2dbc) {
        this.r2dbc = r2dbc;
    }

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
}
