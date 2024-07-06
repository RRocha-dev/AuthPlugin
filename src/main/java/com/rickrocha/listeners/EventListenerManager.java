package com.rickrocha.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.rickrocha.listeners.player.PlayerJoinListener;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventListenerManager {
    private final JavaPlugin plugin;

    public void registerEvents() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), plugin);
    }
}
