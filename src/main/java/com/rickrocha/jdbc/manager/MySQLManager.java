package com.rickrocha.jdbc.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.rickrocha.jdbc.interfaces.DatabaseConfig;
import com.rickrocha.jdbc.interfaces.DatabaseManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MySQLManager implements DatabaseManager {

    private final DatabaseConfig configuration;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(configuration.getJdbcUrl(), configuration.getUsername(),
                configuration.getPassword());
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "uuid VARCHAR(36) NOT NULL, "
                + "username VARCHAR(16) NOT NULL, "
                + "password VARCHAR(255) NOT NULL)";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
