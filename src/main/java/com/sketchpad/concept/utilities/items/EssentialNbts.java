package com.sketchpad.concept.utilities.items;

import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.enchantments.EnchantCollection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class EssentialNbts {
    public static void addAll(ItemStack i, ItemType type, Rarity rarity, String name) {
        NbtManager.addNbt(i, PersistentDataType.STRING, "skyblock", "true");
        NbtManager.addNbt(i, PersistentDataType.STRING, "type", type.name().toLowerCase().replace("_", ""));
        NbtManager.addNbt(i, PersistentDataType.STRING, "rarity", rarity.name().toLowerCase().replace("_", ""));
        NbtManager.addNbt(i, PersistentDataType.STRING, "statable", "true");
        NbtManager.addNbt(i, PersistentDataType.STRING, "name", name);
    }
    public static void addEnchants(ItemStack i, EnchantCollection enchants) {
        HashMap<Enchant, Integer> e = enchants.getAll();
        for (Enchant en:Enchant.values()) {
            if (e.containsKey(en) && e.get(en)!=null)
            NbtManager.addNbt(i, PersistentDataType.INTEGER, en.name().toLowerCase(), e.get(en));
        }
    }
}
