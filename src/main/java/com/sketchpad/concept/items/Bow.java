package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.items.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public enum Bow implements ItemShell {
    JUJU {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(310);
            stats.setStrength(40);
            stats.setCritChance(10);
            stats.setCritDamage(110);
            String[] lore = {ChatColor.GRAY+"This is just a silly test!"};
            Shortbow item = new Shortbow(Rarity.EPIC, "Juju Shortbow", Material.BOW, stats);
            item.setShortbowLore(List.of(lore));
            item.setShortbowColor(ChatColor.DARK_PURPLE);
            this.item = item;
        }
    },
    ;
    public ItemBase item;
    public ItemBase getItem() {
        return item;
    }
    public static void setContained() {
        for (Bow s: Bow.values()) {
            s.setObject();
        }
    }
    public boolean equals(@Nullable ItemStack i) {
        if (i!=null) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("name"), PersistentDataType.STRING)) {
                    return Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING), item.getDisplayName());
                }
            }
        }
        return false;
    }
    public boolean equals(@Nullable ItemBase i) {
        if (i!=null) {
            return i.getDisplayName().equals(item.getDisplayName());
        }
        return false;
    }
}
