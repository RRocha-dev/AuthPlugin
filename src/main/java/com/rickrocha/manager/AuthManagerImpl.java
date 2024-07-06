package com.rickrocha.manager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.rickrocha.manager.interfaces.AuthManager;

public class AuthManagerImpl implements AuthManager {

    private final Set<UUID> authenticatedPlayers = new HashSet<>();

    @Override
    public boolean authenticatePlayer(UUID playerUUID) {
        return authenticatedPlayers.add(playerUUID);
    }

    @Override
    public boolean isPlayerAuthenticated(UUID playerUUID) {
        return authenticatedPlayers.contains(playerUUID);
    }

    @Override
    public void removeAuthenticatedPlayer(UUID playerUUID) {
        authenticatedPlayers.remove(playerUUID);
    }

    @Override
    public Set<UUID> getAuthenticatedPlayers() {
        return new HashSet<>(authenticatedPlayers);
    }

}
