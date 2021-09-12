package com.sketchpad.concept.utilities.player;

import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.Player;

public class ArmorUtilities {
    public static boolean hasFullSet(Player p, Armor r) {
        if (p.getInventory().getHelmet()!=null && p.getInventory().getHelmet().hasItemMeta()) {
            ItemBase helmet = SkyblockItem.fromItemStack(p.getInventory().getHelmet()).getBase();
            if (p.getInventory().getChestplate()!=null && p.getInventory().getChestplate().hasItemMeta()) {
                ItemBase chestplate = SkyblockItem.fromItemStack(p.getInventory().getChestplate()).getBase();
                if (p.getInventory().getLeggings()!=null && p.getInventory().getLeggings().hasItemMeta()) {
                    ItemBase leggings = SkyblockItem.fromItemStack(p.getInventory().getLeggings()).getBase();
                    if (p.getInventory().getBoots()!=null && p.getInventory().getBoots().hasItemMeta()) {
                        ItemBase boots = SkyblockItem.fromItemStack(p.getInventory().getBoots()).getBase();
                        return r.getItems().contains(helmet) && r.getItems().contains(chestplate) && r.getItems().contains(leggings) &&
                                r.getItems().contains(boots);
                    }
                }
            }
        }
        return false;
    }
}
