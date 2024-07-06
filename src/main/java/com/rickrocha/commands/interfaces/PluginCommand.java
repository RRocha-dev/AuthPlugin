package com.rickrocha.commands.interfaces;

import org.bukkit.command.CommandExecutor;

public interface PluginCommand extends CommandExecutor {
    String getName();

    String getDescription();

    String getUsage();
}
