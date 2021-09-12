package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum Sword implements ItemShell {
    ASPECT_OF_THE_DRAGONS {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(225);
            stats.setStrength(100);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"All Monsters in front of you");
            lore.add(ChatColor.GRAY+"take "+ChatColor.GREEN+"700"+ChatColor.GRAY+" damage. Hit");
            lore.add(ChatColor.GRAY+"monsters take large knockback.");
            Ability ability = new Ability(lore, "Dragon Rage", 100,5, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);

            item = new ItemBase(Rarity.LEGENDARY, "Aspect of the Dragons", Material.DIAMOND_SWORD, ItemType.SWORD, ability, stats);
        }
    },
    ASPECT_OF_THE_TEST {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(100,100,100,100,100,100,100,100,100, 100,
                    100,100,100,100,100,100,100, 0);

            List<String> lore1 = new ArrayList<>();
            lore1.add(ChatColor.GRAY+"Launches you into the air!");
            Ability ability = new Ability(lore1, "One", 50,0, Ability.Action.SHIFT_RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);

            List<String> lore2 = new ArrayList<>();
            lore2.add(ChatColor.GRAY+"Strikes you with lightning!");
            Ability ability2 = new Ability(lore2, "Two", 100,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);

            List<Ability> abilities = new ArrayList<>();
            abilities.add(ability);
            abilities.add(ability2);
            item = new ItemBase(Rarity.LEGENDARY, "Aspect of the Test", Material.DIAMOND_SWORD, ItemType.SWORD, abilities, stats);
        }
    },
    ASPECT_OF_THE_INTELLIGENT_TEST {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(100,100,100,100,100,100,100,100,100, 100,
                    100,100,100,100,100,100,100, 0);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.AQUA+"+1,000 ✎ Intelligence"+ChatColor.GRAY+" for");
            lore.add(ChatColor.GREEN+"5 "+ChatColor.GRAY+"seconds!");
            Ability ability = new Ability(lore, "Absolute Testing", 0,10, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);
            item = new ItemBase(Rarity.EPIC, "Aspect of the Intelligent Test", Material.IRON_SWORD, ItemType.SWORD, ability, stats);
        }
    },
    ;
    public ItemBase item;
    public ItemBase getItem() {
        return item;
    }
    public static void setContained() {
        for (Sword s: Sword.values()) {
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
