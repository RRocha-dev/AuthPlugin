package com.rickrocha.manager.interfaces;

import java.util.Set;
import java.util.UUID;

public interface AuthManager {
    public boolean authenticatePlayer (UUID playerUUID);

    public boolean isPlayerAuthenticated (UUID playerUUID);

    public void removeAuthenticatedPlayer(UUID playerUUID);

    public Set<UUID> getAuthenticatedPlayers();
}
