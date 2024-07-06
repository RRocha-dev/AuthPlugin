package com.rickrocha.jdbc.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.rickrocha.jdbc.interfaces.DatabaseConfig;

public class MySQLConfig implements DatabaseConfig {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public MySQLConfig(FileConfiguration config) {
        this.jdbcUrl = "jdbc:mysql://" + config.getString("database.mysql.host") + ":"
                + config.getString("database.mysql.port") + "/" + config.getString("database.mysql.database");
        this.username = config.getString("database.mysql.username");
        this.password = config.getString("database.mysql.password");
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
