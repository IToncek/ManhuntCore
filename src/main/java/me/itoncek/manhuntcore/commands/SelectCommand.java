package me.itoncek.manhuntcore.commands;

import me.itoncek.manhuntcore.ManhuntCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

/*
 * Copyright (c) 2022.
 * All rights reserved to IToncek. If you want to use this code or at least a part of it in
 * your own work / video, please contact IToncek on Discord (IToncek#0201) or
 * via email (toncek2019@gmail.com)
 */

/**
 * Command executor for /select command
 */
public class SelectCommand implements CommandExecutor {
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
        if(! ManhuntCore.ingame) {
            if(sender.isOp()) {
                if(sender instanceof Player) {
                    ((Player) sender).openInventory(generateInventory());
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This generates inventory with all players
     * @return Inventory with heads of all players online
     */
    public Inventory generateInventory(){
        Inventory output = Bukkit.createInventory(null,getNumber(),Component.text(ChatColor.GREEN + "Select player to be a hunter"));
        for (Player p: Bukkit.getOnlinePlayers()){
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerheadmeta = (SkullMeta) playerhead.getItemMeta();
            playerheadmeta.setOwningPlayer(p);
            if(p.isOp())playerheadmeta.setDisplayName(ChatColor.GOLD + p.getName());
            else playerheadmeta.setDisplayName(ChatColor.WHITE + p.getName());

            playerhead.setItemMeta(playerheadmeta);
            output.addItem(playerhead);
        }
        return output;
    }
    public static int getNumber(){
        if(Bukkit.getOnlinePlayers().size() <= 9){
            return 9;
        } else if(Bukkit.getOnlinePlayers().size() <= 9){
            return 18;
        } else if(Bukkit.getOnlinePlayers().size() <= 9){
            return 27;
        } else if(Bukkit.getOnlinePlayers().size() <= 9){
            return 36;
        } else if(Bukkit.getOnlinePlayers().size() <= 9){
            return 45;
        } else if(Bukkit.getOnlinePlayers().size() <= 9){
            return 54;
        } else {
            Bukkit.broadcast(Component.text(ChatColor.DARK_RED+"There is too much players. Please ask at least " + (Bukkit.getOnlinePlayers().size() - 54) + " Players to leave."));
            return 54;
        }
    }
}