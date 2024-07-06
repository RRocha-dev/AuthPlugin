package com.rickrocha.jdbc.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {
    Connection getConnection() throws SQLException;

    void createTable();

    void closeConnection(Connection connection);
}
