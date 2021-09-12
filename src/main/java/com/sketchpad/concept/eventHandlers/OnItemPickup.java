package com.sketchpad.concept.eventHandlers;

import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class OnItemPickup implements Listener {
    @EventHandler
    public void toSkyblock(EntityPickupItemEvent e) {
        SkyblockItem item = SkyblockItem.fromItemStack(e.getItem().getItemStack());
        e.getItem().setItemStack(item.toItemStack());
    }
}
