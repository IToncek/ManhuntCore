/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.popliktroll;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class
 */
public final class PoplikTroll extends JavaPlugin {
    /**
     * Instance of player that is being speedrunner
     */
    public static Player speedrunners;
    /**
     * Position of speedrunners portal
     */
    public static Location portalLoc;
    /**
     * Position of speedrunners portal in the nether
     */
    public static Location portalLocnether;
    /**
     * Instructions for plugin startup
     */
    @Override
    public void onEnable() {
        registerListeners(this);
    }

    /**
     * Registers all listeners
     * @param plugin PoplikTroll plugin instance
     */
    public static void registerListeners(PoplikTroll plugin){
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), plugin);
    }

    /**
     * Instructions for plugin shutdown
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
