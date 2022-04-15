/*******************************************************************************
 * Copyright © 2022 IToncek
 *
 * This software was made by IToncek. You are allowed to use it as long as you give credit to IToncek and link original repo.
 ******************************************************************************/

package me.itoncek.manhuntcore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class StartSequence implements Listener {
    public static boolean listen;

    public static void run() {
        Bukkit.getLogger().info("Initialized");
        listen = true;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if(listen) {
            if(event.getFrom().getBlock().getLocation() != event.getTo().getBlock().getLocation()) {
                if(event.getPlayer() == ManhuntCore.speedrunners) {
                    listen = false;
                } else {
                    event.getPlayer().teleport(ManhuntCore.playerRespawn.get(event.getPlayer()));
                }
            }
        }
    }
}
