package com.sketchpad.concept.eventHandlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.playerData.JsonManager;
import com.sketchpad.concept.playerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class OnInventoryClose implements Listener {
    @EventHandler
    public void saveInventory(InventoryCloseEvent e) {
        if (e.getInventory().getType() == InventoryType.CRAFTING) {
            HashMap<Integer, SkyblockItem> items = new HashMap<>();
            for (int i = 0; i<54;i++) {
                ItemStack is = e.getPlayer().getInventory().getItem(i);
                if (is!=null) {
                    items.put(i, SkyblockItem.fromItemStack(is));
                }
            }
            PlayerData data = Concept.data.get(e.getPlayer().getUniqueId());
            data.setInventory(items);
            Concept.data.put(e.getPlayer().getUniqueId(), data);
            JsonManager.writeAll((Player) e.getPlayer(), data);
        }
    }
}
