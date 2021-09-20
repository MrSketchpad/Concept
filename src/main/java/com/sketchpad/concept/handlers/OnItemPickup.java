package com.sketchpad.concept.handlers;

import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class OnItemPickup implements Listener {
    @EventHandler
    public void toSkyblock(EntityPickupItemEvent e) {
        SkyblockItem item = SkyblockItem.fromItemStack(e.getItem().getItemStack());
        e.getItem().setItemStack(item.toItemStack());
    }
}
