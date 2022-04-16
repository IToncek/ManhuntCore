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

/*
 * Copyright (c) 2022.
 * All rights reserved to IToncek. If you want to use this code or at least a part of it in
 * your own work / video, please contact IToncek on Discord (IToncek#0201) or
 * via email (toncek2019@gmail.com)
 */

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
        if(ManhuntCore.ingame) {
            Player p = event.getPlayer();
            if(! ManhuntCore.speedrunners.equals(p)) {
                Action a = event.getAction();
                if(a.equals(Action.RIGHT_CLICK_BLOCK) || a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
                    if(p.getItemInHand().getType().equals(Material.COMPASS)) {
                        if(ManhuntCore.speedrunners.getWorld().getName().equals(p.getWorld().getName())) {
                            p.setCompassTarget(ManhuntCore.speedrunners.getEyeLocation());
                            p.sendMessage(ChatColor.GREEN + "Tracking " + ManhuntCore.speedrunners.getName());
                        } else if(p.getWorld().getName().equalsIgnoreCase("world") && (ManhuntCore.speedrunners.getWorld().getName().equals("world_nether") || ManhuntCore.speedrunners.getWorld().getName().equals("world_the_end"))) {
                            p.setCompassTarget(ManhuntCore.portalLoc);
                            p.sendMessage(ChatColor.YELLOW + "Tracking " + ManhuntCore.speedrunners.getName() + "'s portal");
                        } else if(p.getWorld().getName().equalsIgnoreCase("world_the_end")) {
                            p.sendMessage(ChatColor.DARK_RED + "Tracking is disabled in the end");
                        } else {
                            p.setCompassTarget(ManhuntCore.portalLocnether);
                            p.sendMessage(ChatColor.YELLOW + "Tracking " + ManhuntCore.speedrunners.getName() + "'s portal");
                        }
                        event.setCancelled(true);
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
        if(ManhuntCore.ingame) {
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
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if(! ManhuntCore.ingame) {
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
}