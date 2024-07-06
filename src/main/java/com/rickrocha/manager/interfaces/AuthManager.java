package com.rickrocha.manager.interfaces;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public interface AuthManager {
    public boolean authenticatePlayer (UUID playerUUID);

    public boolean isPlayerAuthenticated (UUID playerUUID);

    public void removeAuthenticatedPlayer(UUID playerUUID);

    public ConcurrentHashMap<UUID, Boolean> getAuthenticatedPlayers();

    public boolean registerUser(Player player, String username, String password);

    public boolean userExists(Player player);
}
