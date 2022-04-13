/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.popliktroll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * All events are stored here
 */
public class Events implements Listener {

    /**
     * Compass controller
     *
     * @param event Player Interact event
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action a = event.getAction();
        Material i = p.getItemInUse().getType();
        if(! PoplikTroll.speedrunners.equals(p)) {
            if(a.isLeftClick()) {
                if(i.equals(Material.COMPASS)) {
                    if(!p.getWorld().equals(Bukkit.getWorld("world_the_end"))) {
                        event.setCancelled(true);
                        if(PoplikTroll.speedrunners.getWorld() == p.getWorld()) {
                            p.setCompassTarget(PoplikTroll.speedrunners.getLocation());
                            p.sendMessage(ChatColor.GREEN + "Tracking " + PoplikTroll.speedrunners.getName());
                        } else {
                            if(p.getWorld().equals(Bukkit.getWorld("world"))) {
                                p.setCompassTarget(PoplikTroll.portalLoc);
                                p.sendMessage(ChatColor.YELLOW + "Tracking " + PoplikTroll.speedrunners.getName() + "'s portal");
                            } else {
                                p.setCompassTarget(PoplikTroll.portalLocnether);
                                p.sendMessage(ChatColor.YELLOW + "Tracking " + PoplikTroll.speedrunners.getName() + "'s portal");
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.DARK_RED + "You are not allowed to use tracking in the end!");
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerPortal(PlayerPortalEvent event) {
        if(event.getPlayer().equals(PoplikTroll.speedrunners)) {
            if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) || event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                if(event.getFrom().getWorld().equals(Bukkit.getWorld("world"))) {
                    PoplikTroll.portalLoc = event.getFrom();
                } else {
                    PoplikTroll.portalLoc = event.getTo();
                }
                if(event.getFrom().getWorld().equals(Bukkit.getWorld("world_nether"))) {
                    PoplikTroll.portalLocnether = event.getFrom();
                } else {
                    PoplikTroll.portalLocnether = event.getTo();
                }
            }
        }
    }
}
