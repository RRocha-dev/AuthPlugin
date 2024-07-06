package com.rickrocha.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.rickrocha.utils.impl.MessageSenderImpl;
import com.rickrocha.utils.interfaces.MessageSender;

public class PlayerJoinListener implements Listener {

    private final MessageSender messageSender = new MessageSenderImpl();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        player.sendTitle("&aLogin", "Para se autenticar, utilize /login", 3, 5, 3);

        player.sendMessage("&aSeja bem vindo(a) ao nosso servidor &f" + player.getName() + "&a!");
    }
}
