package com.rickrocha;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.rickrocha.commands.LoginCommand;
import com.rickrocha.commands.RegisterCommand;
import com.rickrocha.commands.manager.CommandManager;
import com.rickrocha.jdbc.config.MySQLConfig;
import com.rickrocha.jdbc.config.SqLiteConfig;
import com.rickrocha.jdbc.interfaces.DatabaseConfig;
import com.rickrocha.jdbc.interfaces.DatabaseManager;
import com.rickrocha.jdbc.manager.MySQLManager;
import com.rickrocha.jdbc.manager.SqLiteManager;
import com.rickrocha.listeners.EventListenerManager;
import com.rickrocha.manager.AuthManagerImpl;
import com.rickrocha.manager.interfaces.AuthManager;
import com.rickrocha.utils.impl.MessageSenderImpl;
import com.rickrocha.utils.interfaces.MessageSender;

import lombok.Getter;

public class AuthPlugin extends JavaPlugin {

    private MessageSender messageSender;
    @Getter
    private AuthManager authManager;
    private FileConfiguration config;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        DatabaseManager databaseManager = initializeDatabase();
        databaseManager.createTable();

        messageSender = new MessageSenderImpl();
        authManager = new AuthManagerImpl(databaseManager);

        EventListenerManager listenerManager = new EventListenerManager(this);
        listenerManager.registerEvents();

        commandManager = new CommandManager(this);
        commandManager.registerCommands(Arrays.asList(new RegisterCommand(authManager), new LoginCommand(authManager)));

        messageSender.sendSucefullConsoleMessage("Plugin Iniciado Com Sucesso!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§aPlugin desligado com Sucesso!");
    }

    private DatabaseManager initializeDatabase() {
        String dbType = config.getString("database.type");
        DatabaseConfig databaseConfig;

        if ("mysql".equalsIgnoreCase(dbType)) {
            databaseConfig = new MySQLConfig(config);
            return new MySQLManager(databaseConfig);
        } else {
            databaseConfig = new SqLiteConfig(config);
            return new SqLiteManager(databaseConfig);
        }
    }
}
