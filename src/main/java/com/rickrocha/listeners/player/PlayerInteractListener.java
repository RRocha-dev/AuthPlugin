package com.rickrocha.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.rickrocha.manager.interfaces.AuthManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerInteractListener implements Listener {
    private final AuthManager authManager;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!authManager.isPlayerAuthenticated(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
