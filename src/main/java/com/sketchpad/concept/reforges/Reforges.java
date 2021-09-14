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
    FABLED {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Critical hits have a chance to");
            lore.add(ChatColor.GRAY+"deal up to "+ChatColor.GREEN+"+20%"+ChatColor.GRAY+" extra damage.");

            SkyblockStats common = SkyblockStats.getEmpty();
            common.setCritDamage(15);
            common.setStrength(30);
            SkyblockStats uncommon = SkyblockStats.getEmpty();
            uncommon.setCritDamage(20);
            uncommon.setStrength(35);
            SkyblockStats rare = SkyblockStats.getEmpty();
            rare.setCritDamage(25);
            rare.setStrength(40);
            SkyblockStats epic = SkyblockStats.getEmpty();
            epic.setCritDamage(32);
            epic.setStrength(50);
            SkyblockStats legendary = SkyblockStats.getEmpty();
            legendary.setCritDamage(40);
            legendary.setStrength(60);
            SkyblockStats mythic = SkyblockStats.getEmpty();
            mythic.setCritDamage(50);
            mythic.setStrength(75);

            this.reforge = new Reforge(
                    common, uncommon, rare,
                    epic, legendary, mythic,
                    ItemType.ARMOR, "Fabled", 0, Rarity.RARE,
                    Material.DIAMOND, lore, Rarity.ERROR
            );
        }
    },
    RENOWNED {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases all stats by "+ChatColor.GREEN+"+1%");
            SkyblockStats epic = SkyblockStats.getEmpty();
            epic.setHealth(6);
            epic.setDefense(6);
            epic.setSpeed(1);
            epic.setIntelligence(8);
            epic.setStrength(8);
            epic.setCritDamage(8);
            epic.setCritChance(8);
            epic.setTotalModifier(0.01);
            SkyblockStats legendary = epic.clone();
            legendary.setIntelligence(10);
            legendary.setStrength(10);
            legendary.setCritDamage(10);
            legendary.setCritChance(10);
            legendary.setHealth(8);
            legendary.setDefense(8);
            SkyblockStats mythic = epic.clone();
            mythic.setIntelligence(12);
            mythic.setStrength(12);
            mythic.setCritDamage(12);
            mythic.setCritChance(12);
            mythic.setHealth(10);
            mythic.setDefense(10);
            this.reforge = new Reforge(
                    epic, epic, epic,
                    epic, legendary, mythic,
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
