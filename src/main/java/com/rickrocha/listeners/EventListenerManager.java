package com.rickrocha.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.rickrocha.listeners.player.PlayerInteractListener;
import com.rickrocha.listeners.player.PlayerJoinListener;
import com.rickrocha.listeners.player.PlayerMoveListener;
import com.rickrocha.manager.interfaces.AuthManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventListenerManager {
    private final JavaPlugin plugin;
    private final AuthManager authManager;

    public void registerEvents() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(authManager, plugin), plugin);
        pluginManager.registerEvents(new PlayerMoveListener(authManager), plugin);
        pluginManager.registerEvents(new PlayerInteractListener(authManager), plugin);
    }
}
