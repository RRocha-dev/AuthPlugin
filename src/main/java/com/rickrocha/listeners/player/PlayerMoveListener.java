package com.rickrocha.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.rickrocha.manager.interfaces.AuthManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerMoveListener implements Listener {
    private final AuthManager authManager;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!authManager.isPlayerAuthenticated(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

}
