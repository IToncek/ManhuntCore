package me.itoncek.manhuntcore;

import me.itoncek.manhuntcore.commands.SelectCommand;
import me.itoncek.manhuntcore.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

/*
 * Copyright (c) 2022.
 * All rights reserved to IToncek. If you want to use this code or at least a part of it in
 * your own work / video, please contact IToncek on Discord (IToncek#0201) or
 * via email (toncek2019@gmail.com)
 */

/**
 * Main class
 */
public final class ManhuntCore extends JavaPlugin {
    /**
     * Instance of player that is being speedrunner
     */
    public static Player speedrunners;
    public static boolean ingame;
    /**
     * Position of speedrunners portal
     */
    public static Location portalLoc;
    /**
     * Position of speedrunners portal in the nether
     */
    public static Location portalLocnether;
    /**
     * Shares local ManhuntCore class with other classes
     */
    public static ManhuntCore plugin;
    /**
     * Instructions for plugin startup
     */
    @Override
    public void onEnable() {
        plugin = this;
        ingame = false;
        printLogo(Bukkit.getLogger());
        registerListeners(this);
    }

    /**
     * Registers all listeners
     * @param plugin PoplikTroll plugin instance
     */
    public static void registerListeners(ManhuntCore plugin){
        Objects.requireNonNull(Bukkit.getServer().getPluginCommand("select")).setExecutor(new SelectCommand());
        Objects.requireNonNull(Bukkit.getServer().getPluginCommand("start")).setExecutor(new StartCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), plugin);
    }

    /**
     * Print IToncek into console
     * @param log Default bukkit logger
     */
    public static void printLogo(Logger log){
        log.info("Enabled!");
    }

    /**
     * Instructions for plugin shutdown
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
