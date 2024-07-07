package com.rickrocha.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rickrocha.commands.interfaces.PluginCommand;
import com.rickrocha.manager.interfaces.AuthManager;
import com.rickrocha.utils.impl.MessageSenderImpl;
import com.rickrocha.utils.interfaces.MessageSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginCommand implements PluginCommand {

    private final AuthManager authManager;
    private MessageSender messageSender = new MessageSenderImpl();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                return false;
            }

            if (!authManager.userExists(player)) {
                messageSender.sendPlayerMessage(player, "&cUser not found.");
                return true;
            }

            if (!authManager.validatePassword(args[0], player.getUniqueId())) {
                messageSender.sendPlayerMessage(player, "&cIncorrect password!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                return true;
            }

            if (authManager.authenticatePlayer(player.getUniqueId())) {
                messageSender.sendPlayerMessage(player, "&aLogin successfully.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            } else {
                messageSender.sendPlayerMessage(player, "&cLogin failed.");
            }

            player.setMaxHealth(20.0);
            player.setHealth(20.0);
            player.setInvisible(false);

            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Login Player in server.";
    }

    @Override
    public String getUsage() {
        return "/login <password>";
    }

}
