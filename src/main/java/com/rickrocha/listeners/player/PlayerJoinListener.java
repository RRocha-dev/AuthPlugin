package com.rickrocha.listeners.player;

import org.bukkit.GameMode;
import org.bukkit.Sound;
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

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(0.1);
        player.setMaxHealth(0.1);
        player.setInvulnerable(true);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        if (!authManager.userExists(player)) {
            messageSender.sendPlayerMessage(player, "&aTo log in you need to register");
        } else {
            messageSender.sendPlayerMessage(player,
                    "&aHello again &f" + player.getName() + "&a, use &f/login <password> &ato authenticate.");
        }

        new BukkitRunnable() {
            int countdown = 60;

            @Override
            public void run() {
                if (authManager.isPlayerAuthenticated(player.getUniqueId())) {
                    messageSender.sendPlayerMessage(player, "&aWelcome to our server &f" + player.getName() + "&a!");
                    cancel();
                    return;
                }

                String color = (countdown % 2 == 0) ? "&c" : "&e";
                if (!authManager.userExists(player)) {
                    messageSender.sendPlayerTitle(player, color + "Register",
                            "To register, use /register in " + countdown + " seconds");
                } else {
                    messageSender.sendPlayerTitle(player, color + "Login",
                            "To authenticate, use /login in " + countdown + " seconds");
                }

                if (countdown <= 0) {
                    player.kickPlayer("§cYou didn't authenticate in time!");
                    cancel();
                    return;
                }
                countdown--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
