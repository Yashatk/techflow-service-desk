package com.techflow.ws.sys.domain.dto;

public class DatabaseConnectionDTO {

    private String connectionString;
    private String username;
    private String password;

    public String getConnectionString() {
        return connectionString;
    }

    public String getPassword() {
        return password;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
