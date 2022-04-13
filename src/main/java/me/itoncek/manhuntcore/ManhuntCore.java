/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
        printLogo(Bukkit.getLogger());
        registerListeners(this);
    }

    /**
     * Registers all listeners
     * @param plugin PoplikTroll plugin instance
     */
    public static void registerListeners(ManhuntCore plugin){
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), plugin);
    }

    /**
     * Print IToncek into console
     * @param log Default bukkit logger
     */
    public static void printLogo(Logger log){
        log.info("                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                                                                                                                  \n" +
                "                                                                            ..                                                                                    \n" +
                "                                                                ...         ..   .    ..                                                                          \n" +
                "                                                                  ..............  .. ..                                                                           \n" +
                "                                                                          .   .........                                                                           \n" +
                "                                                                     °OOO*.                 .                                                                     \n" +
                "                                                                     o@@@#oooO##o.°*****°°.                                                                       \n" +
                "                                                                   ° O@@@@@*#@@@@@°@@@@##@o @                                                                     \n" +
                "                                                                     O@@@@@ #@@@@@.@@@####O                                                                       \n" +
                "                                                                     #@@@@@@@@@@@@@@@@####O                                                                       \n" +
                "                                                                     #@@@@@@@@@@@@@@@######. @                                                                    \n" +
                "                                                                     *@@@@@@@@@@@##O**#####.                                                                      \n" +
                "                                                                @  ** o@@@@@@@@@@OOOO#####o .                                                                     \n" +
                "                                                                  °*oo°*O#@@@@@@@@@######o.°*.                                                                    \n" +
                "                                                                  °*ooOo  °°°°*°*°°°°°°°..***°                                                                    \n" +
                "                                                                  .oooOO*..°°*° O* °°°. .*oo*. @                                                                  \n" +
                "                                                                   .*ooOOo****°.°°.**°°*oOO*.                                                                     \n" +
                "                                                                    ..°*oooOooooooOoooOOOo°.                                                                      \n" +
                "                                                                 .*o*°°.....*******oooo°°..°°°°                                                                   \n" +
                "                                                                *#####OOoo*..o********.°**oooOOo.                                                                 \n" +
                "                                                                #@@@@@@@@#@°*@#######O.######@@#o °                                                               \n" +
                "                                                               °@@@@@@@@@@@*o@@@@@@@@@°#@@@@@@@@#.                                                                \n" +
                "                                                               *@@@@@@@@@@@o*@@@@@@@@@o*@@#@@@@@@°                                                                \n" +
                "                                                               o@@@@@*o@@@@@O@@@@@@@@@O*@@*o@@@@@o                                                                \n" +
                "                                                               *@@@@@**@@@@@@@@@@@@@@@@@@@#.@@@@@o                                                                \n" +
                "                                                               °@#@#@°*################@@@#.o###Oo                                                                \n" +
                "                                                               .OOOoO°°ooooooooooooooooO#Oo..***°.                                                                \n" +
                "                                                                °....  .......  ........ ..                                                                       \n");
    }

    /**
     * Instructions for plugin shutdown
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
