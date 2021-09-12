package com.sketchpad.concept.eventHandlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.inventories.SkyblockMenus;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.sql.Ref;
import java.util.Objects;

public class MenuHandler implements Listener {
    @EventHandler
    public void anvil(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCursor()!=null) {
            ItemStack i = e.getCursor();
            if (e.getView().getTitle().equals("Anvil")) {
                if (e.getSlot()==29) {
                    SkyblockInventory inventory = SkyblockMenus.reforgeMenu(p, SkyblockMenus.ReforgeStatus.LEFT_SUCCESS);
                    if (Objects.requireNonNull(Objects.requireNonNull(e.getClickedInventory()).getItem(14)).getType()==Material.GREEN_STAINED_GLASS_PANE) {
                        inventory = SkyblockMenus.reforgeMenu(p, SkyblockMenus.ReforgeStatus.SUCCESS);
                        inventory.removeClear(33);
                        inventory.setItem(33, SkyblockItem.fromItemStack(Objects.requireNonNull(e.getClickedInventory().getItem(33))));
                    }
                    inventory.removeClear(29);
                    inventory.setItem(29, SkyblockItem.fromItemStack(i));
                    inventory.open(p);
                }
                else if (e.getSlot()==33) {
                    SkyblockInventory inventory = SkyblockMenus.reforgeMenu(p, SkyblockMenus.ReforgeStatus.RIGHT_SUCCESS);
                    if (Objects.requireNonNull(Objects.requireNonNull(e.getClickedInventory()).getItem(11)).getType()== Material.GREEN_STAINED_GLASS_PANE) {
                        inventory = SkyblockMenus.reforgeMenu(p, SkyblockMenus.ReforgeStatus.SUCCESS);
                        inventory.removeClear(29);
                        inventory.setItem(29, SkyblockItem.fromItemStack(Objects.requireNonNull(e.getClickedInventory().getItem(29))));
                    }
                    inventory.removeClear(33);
                    inventory.setItem(33, SkyblockItem.fromItemStack(i));
                    inventory.open(p);
                }
            }
        }
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (e.getView().getTitle().equals("Anvil")) {

            }
        }
    }
    @EventHandler
    public void skyblockMenu(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("Skyblock Menu") && e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (i.equals(new SkyblockItem(InventoryItems.reforgeButton()).toItemStack())) SkyblockMenus.reforgeMenu((Player) e.getWhoClicked(),
                    SkyblockMenus.ReforgeStatus.FAIL).open((Player)
            e.getWhoClicked());
        }
    }
    @EventHandler
    public void allMenus(InventoryClickEvent e) {
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();
            if (i.hasItemMeta()) {
                if (SkyblockItem.fromItemStack(i).getType()==ItemType.INVENTORY) {
                    e.setCancelled(true);
                }
            }
            if (i.equals(new SkyblockItem(InventoryItems.close()).toItemStack())) {
                e.setCancelled(true);
                p.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
            }
        }
    }
    @EventHandler
    public void itemsInventory(InventoryClickEvent e) {
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (i.hasItemMeta() && NbtManager.hasNbt(i, PersistentDataType.STRING, "name")) {
                if (Objects.equals(NbtManager.getNbt(i, PersistentDataType.STRING, "name"), "Skyblock Menu")) {
                    e.setCancelled(true);
                }
            }
            if (i.equals(new SkyblockItem(InventoryItems.menuGlass()).toItemStack()) ||
                    (i.hasItemMeta() && i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("type"), PersistentDataType.STRING) &&
                            Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("type"), PersistentDataType.STRING), "filler"))){
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equals("Skyblock Menu")) e.setCancelled(true);
            else if (e.getView().getTitle().contains("Items")) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().addItem(i);
                e.getWhoClicked().sendMessage(ChatColor.GREEN+"Gave you "+i.getItemMeta().getDisplayName());
            }
        }
    }
}
