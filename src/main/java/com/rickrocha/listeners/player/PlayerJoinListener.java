package com.rickrocha.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.rickrocha.manager.interfaces.AuthManager;
import com.rickrocha.utils.impl.MessageSenderImpl;
import com.rickrocha.utils.interfaces.MessageSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final MessageSender messageSender = new MessageSenderImpl();
    private final AuthManager authManager;
    private final Plugin plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        new BukkitRunnable() {
            int countdown = 30;

            @Override
            public void run() {
                if (authManager.isPlayerAuthenticated(player.getUniqueId())) {
                    messageSender.sendPlayerMessage(player, "&aWelcome to our server &f" + player.getName() + "&a!");
                    cancel();
                    return;
                }

                String color = (countdown % 2 == 0) ? "&c" : "&e";
                player.sendTitle(color + "Login", "To authenticate, use /login", 3, 5, 3);

                if (countdown <= 0) {
                    player.kickPlayer("&cYou didn't authenticate in time!");
                    cancel();
                    return;
                }
                countdown--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
