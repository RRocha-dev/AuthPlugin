package com.rickrocha.jdbc.config;

import org.bukkit.configuration.file.FileConfiguration;

import com.rickrocha.jdbc.interfaces.DatabaseConfig;

public class SqLiteConfig implements DatabaseConfig {

    private final String jdbcUrl;

    public SqLiteConfig(FileConfiguration config) {
        this.jdbcUrl = "jdbc:sqlite:" + config.getString("database.sqlite.file");
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

}
