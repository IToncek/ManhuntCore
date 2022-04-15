/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore.commands;

import me.itoncek.manhuntcore.ManhuntCore;
import me.itoncek.manhuntcore.StartSequence;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class StartCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     *
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.getLogger().info(sender.getName() + "/" + ManhuntCore.speedrunners.getName());
        if(sender.getName().equals(ManhuntCore.speedrunners.getName())){
            Logger console = Bukkit.getLogger();
            console.info("Okay, starting");
            Location baseloc = ManhuntCore.speedrunners.getLocation();
            ManhuntCore.speedrunners.setBedSpawnLocation(ManhuntCore.speedrunners.getLocation());
            console.info("Speedrunner's respawn location was set");
            int NUM_POINTS = Bukkit.getOnlinePlayers().size()-1;
            int RADIUS = 4;
            int i = NUM_POINTS;
            console.info("Generating spawn circle");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p != ManhuntCore.speedrunners) {
                    final double angle = Math.toRadians(((double) i / NUM_POINTS) * 360d);
                    Location locat = new Location(Bukkit.getWorld("world"), Math.cos(angle) * RADIUS + baseloc.getBlockX(), baseloc.getBlockY(), Math.sin(angle) * RADIUS + baseloc.getBlockZ());
                    ManhuntCore.playerRespawn.put(p, locat);
                    p.setBedSpawnLocation(locat);
                    console.info("Done for " + p.getName());
                    i++;
                }
            }
            console.info("Starting start sequence");
            StartSequence.run();
            return true;
        } else {
            return false;
        }
    }
}
