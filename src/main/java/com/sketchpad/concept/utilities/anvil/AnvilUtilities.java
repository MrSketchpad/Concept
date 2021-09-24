package com.sketchpad.concept.utilities.anvil;

import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.items.Materials;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AnvilUtilities {
    public static class AnvilItem {
        public SkyblockItem item;
        public boolean applied;
        public AnvilItem(SkyblockItem item, boolean applied) {
            this.item = item;
            this.applied = applied;
        }
    }
    public static AnvilItem evaluateUpgrade(SkyblockItem l, SkyblockItem r, Player p, boolean sendMessage) {
        SkyblockItem newItem = l.clone();
        if (newItem.getBase()==r.getBase()) {
            newItem.setEnchants(newItem.getEnchants().combine(r.getEnchants()));
            if (r.isRecombobulated()) newItem.setRecombobulated(true);
            else if (newItem.isRecombobulated()) newItem.setRecombobulated(true);
            if (r.getHotPotatoBooks()>newItem.getHotPotatoBooks()) newItem.setHotPotatoBooks(r.getHotPotatoBooks());
            return new AnvilItem(newItem, true);
        } else if (r.getBase() == Materials.RECOMBOBULATOR.item) {
            if (newItem.isRecombobulated()) {
                p.sendMessage(ChatColor.RED+"You can only recombobulate an item once!");
                return new AnvilItem(newItem, false);
            } else {
                newItem.setRecombobulated(true);
                return new AnvilItem(newItem, true);
            }
        }
        else if (r.getBase() == Materials.HOT_POTATO_BOOK.item) {
            if (newItem.getHotPotatoBooks()>=10) {
                p.sendMessage(ChatColor.RED+"You already have the maximum amount of this upgrade applied to your item!");
                return new AnvilItem(newItem, false);
            } else {
                newItem.setHotPotatoBooks(newItem.getHotPotatoBooks()+1);
                return new AnvilItem(newItem, true);
            }
        } else if (r.getBase() == Materials.FUMING_POTATO_BOOK.item) {
            if (newItem.getFumingPotatoBooks()>=5) {
                p.sendMessage(ChatColor.RED+"You already have the maximum amount of this upgrade applied to your item!");
                return new AnvilItem(newItem, false);
            } else {
                newItem.setFumingPotatoBooks(newItem.getFumingPotatoBooks()+1);
                return new AnvilItem(newItem, true);
            }
        }
        else if (r.getBase().getType() == ItemType.REFORGE_STONE) {
            if (Reforges.valueOf(r.getBase().getDisplayName().toUpperCase()).getReforge().getType().getTypes().contains(newItem.getType())) {
                newItem.setReforge(Reforges.valueOf(r.getBase().getDisplayName().toUpperCase()).getReforge());
                return new AnvilItem(newItem, true);
            } else {
                p.sendMessage(ChatColor.RED+"This reforge can only be applied to "+
                        Reforges.valueOf(r.getBase().getDisplayName().toUpperCase()).getReforge().getType().name().toLowerCase()+"!");
                return new AnvilItem(newItem, false);
            }
        }
        else if (sendMessage) {
            p.sendMessage(ChatColor.RED+"You cannot combine these items!");
        }
        return new AnvilItem(new SkyblockItem(InventoryItems.centerReforgeItem()), false);
    }
}
