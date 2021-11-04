package com.sketchpad.concept.utilities.player;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.jetbrains.annotations.NotNull;

public class TeleportPlayer {
    public static @NotNull
    Location raycast(@NotNull LivingEntity from, int distance, boolean sendMessage) {
        try {
            Location eyes = from.getEyeLocation();
            BlockIterator iterator = new BlockIterator(from.getLocation(), 1, distance);
            while (iterator.hasNext()) {
                Location loc = iterator.next().getLocation();
                if (loc.getBlock().getType().isSolid()) {
                    if (loc.equals(from.getLocation()) && sendMessage) {
                        from.sendMessage(ChatColor.RED+"There are blocks in the way!");
                    }
                    loc.setPitch(eyes.getPitch());
                    loc.setYaw(eyes.getYaw());
                    loc.setY(loc.getY());
                    return loc;
                }
            }
            Location n = from.getEyeLocation().clone().add(from.getEyeLocation().getDirection().multiply(distance));
            n.setPitch(eyes.getPitch());
            n.setYaw(eyes.getYaw());
            n.setY(n.getY()+1);
            return n;
        } catch (IllegalStateException e) {
            if (sendMessage) from.sendMessage(ChatColor.RED+"There are blocks in the way!");
            return from.getLocation();
        }
    }
    public static void teleport(@NotNull Player p, int amount, boolean sendMessage) {
        p.teleport(raycast(p, amount, sendMessage));
    }
}
