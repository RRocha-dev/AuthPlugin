package com.rickrocha.utils.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.rickrocha.utils.interfaces.MessageSender;
import com.rickrocha.utils.interfaces.MessageUtils;

public class MessageSenderImpl implements MessageSender {

    private final MessageUtils messageUtils = new MessageUtilsImpl();

    @Override
    public void sendPlayerMessage(Player player, String message) {
        if (player != null)
            player.sendMessage(messageUtils.convertCharacterToConsole(message));
    }

    @Override
    public void sendSucefullConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(messageUtils.convertCharacterToConsole("&a&l[SUCCESS]&a " + message));
    }

    @Override
    public void sendErrorConsoleMessage(String message) {

        Bukkit.getConsoleSender().sendMessage(messageUtils.convertCharacterToConsole("&c&l[ERROR]&c " + message));
    }

    @Override
    public void sendWarningConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(messageUtils.convertCharacterToConsole("&e&l[WARNING]&a " + message));
    }

    @Override
    public void sendPlayerTitle(Player player, String title, String message) {
        player.sendTitle(messageUtils.convertCharacterToConsole(title), messageUtils.convertCharacterToConsole(message),
                5, 10, 5);
    }

}
