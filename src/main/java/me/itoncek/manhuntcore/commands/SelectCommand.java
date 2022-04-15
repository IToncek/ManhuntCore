/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore.commands;

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
        if(sender.isOp()) {
            if(sender instanceof Player){
                ((Player) sender).openInventory(generateInventory());
            }
            return true;
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
