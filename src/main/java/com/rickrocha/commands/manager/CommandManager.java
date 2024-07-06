package com.rickrocha.commands.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.rickrocha.commands.interfaces.PluginCommand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandManager {
    private final JavaPlugin plugin;
    @Getter
    private final List<PluginCommand> commands = new ArrayList<>();

    public void registerCommand(PluginCommand command) {
        plugin.getCommand(command.getName()).setExecutor(command);
        commands.add(command);
    }

    public void registerCommands(List<PluginCommand> commands) {
        for (PluginCommand command : commands) {
            registerCommand(command);
        }
    }
}
