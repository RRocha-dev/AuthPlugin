package com.rickrocha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import com.rickrocha.dao.interfaces.CRUDBase;
import com.rickrocha.jdbc.interfaces.DatabaseManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthDAO implements CRUDBase {

    private static final String FIELDS = " uuid, username, password ";
    private static final String TABLE = " users ";
    private static final String SQL_INSERT = " INSERT INTO " + TABLE + "(" + FIELDS + ") VALUES (?, ?, ?)";
    private static final String SQL_DELETE = " DELETE FROM " + TABLE + " WHERE uuid = ?";
    private static final String BASE_SELECT = " SELECT " + FIELDS + " FROM " + TABLE;
    private static final String SQL_SELECT_BY_UUID = BASE_SELECT + " WHERE uuid = ?";
    private static final String SQL_GET_PASSWORD = " SELECT password FROM " + TABLE + " WHERE uuid = ?";

    private final DatabaseManager databaseManager;

    @Override
    public boolean insert(Connection connection, UUID uuid, String username, String password) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT)) {
            int i = 1;
            prepareStatement.setString(i++, uuid.toString());
            prepareStatement.setString(i++, username);
            prepareStatement.setString(i++, password);
            prepareStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Connection connection, Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Connection connection, UUID uuid) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            int i = 1;
            preparedStatement.setString(i++, uuid.toString());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExists(Connection connection, UUID uuid) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_UUID)) {
            int i = 1;
            preparedStatement.setString(i++, uuid.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPasswordUser(Connection connection, UUID uuid) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PASSWORD)) {
            int i = 1;
            preparedStatement.setString(i++, uuid.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
