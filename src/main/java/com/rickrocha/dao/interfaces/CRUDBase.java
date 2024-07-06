package com.rickrocha.dao.interfaces;

import java.sql.Connection;
import java.util.UUID;

public interface CRUDBase {
    public boolean insert(Connection connection, UUID uuid, String username, String password);
    public boolean update(Connection connection, Object object);
    public boolean delete(Connection connection, UUID uuid);
}
