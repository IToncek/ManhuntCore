/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore.commands;

import me.itoncek.manhuntcore.ManhuntCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
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
        if(ManhuntCore.speedrunners != null) {
            if(sender.getName().equals(ManhuntCore.speedrunners.getName())) {
                Logger console = Bukkit.getLogger();
                console.info("Okay, starting");
                for (Player p:Bukkit.getOnlinePlayers()) {
                    if(! p.getName().equals(ManhuntCore.speedrunners.getName())) {
                        for(ItemStack stack : p.getInventory()) {
                            if(stack != null) stack.setAmount(0);
                        }
                        p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
                    }
                }
                ManhuntCore.ingame = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
