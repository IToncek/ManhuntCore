/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore;

import me.itoncek.manhuntcore.commands.SelectCommand;
import me.itoncek.manhuntcore.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Main class
 */
public final class ManhuntCore extends JavaPlugin {
    /**
     * Instance of player that is being speedrunner
     */
    public static Player speedrunners;
    /**
     * Hashmap with spawn locations for all players
     */
    public static HashMap<Player, Location> playerRespawn = new HashMap<>();
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
        log.info("""
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                                                                                                                 \s
                                                                                            ..                                                                                   \s
                                                                                ...         ..   .    ..                                                                         \s
                                                                                  ..............  .. ..                                                                          \s
                                                                                          .   .........                                                                          \s
                                                                                     °OOO*.                 .                                                                    \s
                                                                                     o@@@#oooO##o.°*****°°.                                                                      \s
                                                                                   ° O@@@@@*#@@@@@°@@@@##@o @                                                                    \s
                                                                                     O@@@@@ #@@@@@.@@@####O                                                                      \s
                                                                                     #@@@@@@@@@@@@@@@@####O                                                                      \s
                                                                                     #@@@@@@@@@@@@@@@######. @                                                                   \s
                                                                                     *@@@@@@@@@@@##O**#####.                                                                     \s
                                                                                @  ** o@@@@@@@@@@OOOO#####o .                                                                    \s
                                                                                  °*oo°*O#@@@@@@@@@######o.°*.                                                                   \s
                                                                                  °*ooOo  °°°°*°*°°°°°°°..***°                                                                   \s
                                                                                  .oooOO*..°°*° O* °°°. .*oo*. @                                                                 \s
                                                                                   .*ooOOo****°.°°.**°°*oOO*.                                                                    \s
                                                                                    ..°*oooOooooooOoooOOOo°.                                                                     \s
                                                                                 .*o*°°.....*******oooo°°..°°°°                                                                  \s
                                                                                *#####OOoo*..o********.°**oooOOo.                                                                \s
                                                                                #@@@@@@@@#@°*@#######O.######@@#o °                                                              \s
                                                                               °@@@@@@@@@@@*o@@@@@@@@@°#@@@@@@@@#.                                                               \s
                                                                               *@@@@@@@@@@@o*@@@@@@@@@o*@@#@@@@@@°                                                               \s
                                                                               o@@@@@*o@@@@@O@@@@@@@@@O*@@*o@@@@@o                                                               \s
                                                                               *@@@@@**@@@@@@@@@@@@@@@@@@@#.@@@@@o                                                               \s
                                                                               °@#@#@°*################@@@#.o###Oo                                                               \s
                                                                               .OOOoO°°ooooooooooooooooO#Oo..***°.                                                               \s
                                                                                °....  .......  ........ ..                                                                      \s
                """);
    }

    /**
     * Instructions for plugin shutdown
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
