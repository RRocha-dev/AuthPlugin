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
public class RegisterCommand implements PluginCommand {

    private final AuthManager authManager;
    private MessageSender messageSender = new MessageSenderImpl();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                return false;
            }

            if (!args[0].equals(args[1])) {
                messageSender.sendPlayerMessage(player, "&cPassword's don't match.");
                return false;
            }

            if (authManager.registerUser(player, player.getName(), args[1])) {
                messageSender.sendPlayerMessage(player, "&aSuccessfully registered!");
            } else {
                messageSender.sendPlayerMessage(player, "&cPlayer already registered.");
            }

            return true;
        }

        return false;
    }

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public String getDescription() {
        return "Register a new user.";
    }

    @Override
    public String getUsage() {
        return "/register <password> <confirm password>";
    }

}
