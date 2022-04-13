/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.popliktroll;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {

    /**
     * Compass controller
     * @param event Player Interact event
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action a = event.getAction();
        Material i = p.getItemInUse().getType();
        if(!PoplikTroll.speedrunners.equals(p)){
            if(a.isLeftClick()){
                if(i.equals(Material.COMPASS)){
                    event.setCancelled(true);
                    p.setCompassTarget(PoplikTroll.speedrunners.getLocation());
                    p.sendMessage(ChatColor.GREEN + "Tracking " + PoplikTroll.speedrunners.getLocation());
                };
            }
        }
    }
}
