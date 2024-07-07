package com.rickrocha.utils.interfaces;

import org.bukkit.entity.Player;

public interface MessageSender {
    void sendPlayerMessage(Player player, String message);

    void sendSucefullConsoleMessage(String message);

    void sendErrorConsoleMessage(String message);

    void sendWarningConsoleMessage(String message);

    void sendPlayerTitle(Player player, String title, String message);
}
