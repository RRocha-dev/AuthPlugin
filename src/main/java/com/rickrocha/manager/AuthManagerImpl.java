package com.rickrocha.manager;

import java.sql.Connection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.rickrocha.dao.AuthDAO;
import com.rickrocha.jdbc.interfaces.DatabaseManager;
import com.rickrocha.manager.interfaces.AuthManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthManagerImpl implements AuthManager {

    private final AuthDAO authDAO;
    private final ConcurrentHashMap<UUID, Boolean> authenticatedPlayer = new ConcurrentHashMap<>();
    private final DatabaseManager databaseManager;

    public AuthManagerImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.authDAO = new AuthDAO(databaseManager);
    }

    @Override
    public boolean authenticatePlayer(UUID playerUUID) {
        if (!isPlayerAuthenticated(playerUUID)) {
            return this.authenticatedPlayer.put(playerUUID, true);
        }
        return false;
    }

    @Override
    public boolean isPlayerAuthenticated(UUID playerUUID) {
        return this.authenticatedPlayer.get(playerUUID) != null;
    }

    @Override
    public void removeAuthenticatedPlayer(UUID playerUUID) {
        this.authenticatedPlayer.remove(playerUUID);
    }

    @Override
    public ConcurrentHashMap<UUID, Boolean> getAuthenticatedPlayers() {
        return this.authenticatedPlayer;
    }

    @Override
    public boolean registerUser(Player player, String username, String password) {
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            if (authDAO.userExists(connection, player.getUniqueId()))
                return false;

            return authDAO.insert(connection, player.getUniqueId(), username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean userExists(Player player) {
        Connection connection = null;
        try {
            connection = databaseManager.getConnection();
            return authDAO.userExists(connection, player.getUniqueId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection(connection);
        }
        return false;
    }

}
