package com.rickrocha.commands;

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
                messageSender.sendPlayerMessage(player, "&cUsuário não está cadastrado");
            }

            if (authManager.authenticatePlayer(player.getUniqueId())) {
                messageSender.sendPlayerMessage(player, "&aLogin successfully.");
            } else {
                messageSender.sendPlayerMessage(player, "&cLogin failed.");
            }
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
