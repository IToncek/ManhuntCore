/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

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
        if(! ManhuntCore.speedrunners.equals(p)) {
            Action a = event.getAction();
            if(a.isLeftClick()) {
                Material i = Objects.requireNonNull(p.getItemInUse()).getType();
                if(i.equals(Material.COMPASS)) {
                    if(! p.getWorld().equals(Bukkit.getWorld("world_the_end"))) {
                        event.setCancelled(true);
                        if(ManhuntCore.speedrunners.getWorld() == p.getWorld()) {
                            p.setCompassTarget(ManhuntCore.speedrunners.getLocation());
                            p.sendMessage(ChatColor.GREEN + "Tracking " + ManhuntCore.speedrunners.getName());
                        } else {
                            if(p.getWorld().equals(Bukkit.getWorld("world"))) {
                                p.setCompassTarget(ManhuntCore.portalLoc);
                                p.sendMessage(ChatColor.YELLOW + "Tracking " + ManhuntCore.speedrunners.getName() + "'s portal");
                            } else {
                                p.setCompassTarget(ManhuntCore.portalLocnether);
                                p.sendMessage(ChatColor.YELLOW + "Tracking " + ManhuntCore.speedrunners.getName() + "'s portal");
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.DARK_RED + "You are not allowed to use tracking in the end!");
                    }
                }
            }
        }
    }

    /**
     * Portal location replacer
     *
     * @param event Portal Travel Event
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerPortal(PlayerPortalEvent event) {
        if(event.getPlayer().equals(ManhuntCore.speedrunners)) {
            if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) || event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                if(event.getFrom().getWorld().equals(Bukkit.getWorld("world"))) {
                    ManhuntCore.portalLoc = event.getFrom();
                } else {
                    ManhuntCore.portalLoc = event.getTo();
                }
                if(event.getFrom().getWorld().equals(Bukkit.getWorld("world_nether"))) {
                    ManhuntCore.portalLocnether = event.getFrom();
                } else {
                    ManhuntCore.portalLocnether = event.getTo();
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getView().title().equals(Component.text(ChatColor.GREEN + "Select player to be a hunter"))) {
            if(event.getAction().equals(InventoryAction.PICKUP_ALL) || event.getAction().equals(InventoryAction.PICKUP_HALF) || event.getAction().equals(InventoryAction.PICKUP_ONE) || event.getAction().equals(InventoryAction.PICKUP_SOME)) {
                SkullMeta playerheadmeta = (SkullMeta) Objects.requireNonNull(event.getCurrentItem()).getItemMeta();
                if(Objects.requireNonNull(playerheadmeta.getOwningPlayer()).isOnline()) {
                    ManhuntCore.speedrunners = playerheadmeta.getOwningPlayer().getPlayer();
                    assert ManhuntCore.speedrunners != null;
                    Bukkit.broadcast(Component.text("Speedrunner will be: " + ManhuntCore.speedrunners.getName()));
                }
                event.setCancelled(true);
            }
        }
    }
}