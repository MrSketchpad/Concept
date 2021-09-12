package com.sketchpad.concept.reforges;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.reforges.Reforge;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum Reforges implements ItemShell {
    RENOWNED {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases all stats by "+ChatColor.GREEN+"+1%");
            SkyblockStats base = SkyblockStats.getEmpty();
            base.setHealth(6);
            base.setDefense(6);
            base.setSpeed(1);
            base.setIntelligence(8);
            base.setStrength(8);
            base.setCritDamage(8);
            base.setCritChance(8);
            base.setTotalModifier(0.01);
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setIntelligence(2);
            stats.setStrength(2);
            stats.setCritDamage(2);
            stats.setCritChance(2);
            stats.setHealth(2);
            stats.setDefense(2);
            this.reforge = new Reforge(
                    base, stats,
                    ItemType.ARMOR, "Renowned", 0, Rarity.LEGENDARY,
                    Material.DIAMOND, lore, Rarity.EPIC
            );
        }
    },
    ;
    Reforge reforge;
    public Reforge getReforge() {
        return reforge;
    }
    public boolean equals(@Nullable ItemStack i) {
        if (i!=null) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("name"), PersistentDataType.STRING)) {
                    return Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING), reforge.toBaseItem().getDisplayName());
                }
            }
        }
        return false;
    }
    public boolean equals(@Nullable ItemBase i) {
        if (i!=null) {
            return i.getDisplayName().equals(reforge.toBaseItem().getDisplayName());
        }
        return false;
    }
    public static void setContained() {
        for (Reforges r:Reforges.values()) {
            r.setObject();
        }
    }
}
