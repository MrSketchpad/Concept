package com.sketchpad.concept.handlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.playerData.JsonManager;
import com.sketchpad.concept.playerData.PlayerData;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;

public class OnInventoryClose implements Listener {
    @EventHandler
    public void giveItem(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals("Enchant Item") || e.getView().getTitle().equals("Choose Level")) {
            if (e.getReason()!= InventoryCloseEvent.Reason.OPEN_NEW) {
                if (MenuHandler.enchants.containsKey(e.getPlayer().getUniqueId())) {
                    if (e.getPlayer().getInventory().firstEmpty()!=-1) {
                        e.getPlayer().getInventory().addItem(MenuHandler.enchants.get(e.getPlayer().getUniqueId()).toItemStack());
                    } else e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), MenuHandler.enchants.get(e.getPlayer().getUniqueId()).toItemStack());
                    MenuHandler.enchants.remove(e.getPlayer().getUniqueId());
                }
            }
        } else if (e.getView().getTitle().equals("Anvil")) {
            if (e.getReason() != InventoryCloseEvent.Reason.OPEN_NEW) {
                if (e.getInventory().getItem(29)!=null) e.getPlayer().getInventory().addItem(Objects.requireNonNull(e.getInventory().getItem(29)));
                if (e.getInventory().getItem(33)!=null) e.getPlayer().getInventory().addItem(Objects.requireNonNull(e.getInventory().getItem(33)));
                if (e.getInventory().getItem(13)!=null && !Objects.equals(e.getInventory().getItem(13), new SkyblockItem(InventoryItems.centerReforgeItem()).
                        toItemStack()) && Objects.requireNonNull(e.getInventory().getItem(53)).getType() == Material.RED_STAINED_GLASS_PANE)
                    e.getPlayer().getInventory().addItem(Objects.requireNonNull(e.getInventory().getItem(13)));
            }
        }
    }
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
