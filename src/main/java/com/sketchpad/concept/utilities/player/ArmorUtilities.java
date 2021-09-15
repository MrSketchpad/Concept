package com.sketchpad.concept.utilities.player;

import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.LivingEntity;

public class ArmorUtilities {
    public static boolean hasFullSet(LivingEntity en, Armor r) {
        if (en.getEquipment().getHelmet()!=null && en.getEquipment().getHelmet().hasItemMeta()) {
            ItemBase helmet = SkyblockItem.fromItemStack(en.getEquipment().getHelmet()).getBase();
            if (en.getEquipment().getChestplate()!=null && en.getEquipment().getChestplate().hasItemMeta()) {
                ItemBase chestplate = SkyblockItem.fromItemStack(en.getEquipment().getChestplate()).getBase();
                if (en.getEquipment().getLeggings()!=null && en.getEquipment().getLeggings().hasItemMeta()) {
                    ItemBase leggings = SkyblockItem.fromItemStack(en.getEquipment().getLeggings()).getBase();
                    if (en.getEquipment().getBoots()!=null && en.getEquipment().getBoots().hasItemMeta()) {
                        ItemBase boots = SkyblockItem.fromItemStack(en.getEquipment().getBoots()).getBase();
                        return r.getItems().contains(helmet) && r.getItems().contains(chestplate) && r.getItems().contains(leggings) &&
                                r.getItems().contains(boots);
                    }
                }
            }
        }
        return false;
    }
}
