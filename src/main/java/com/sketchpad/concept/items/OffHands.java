package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.Ability;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum OffHands implements ItemShell {
    ARMAGEDDON {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(375,0,245,0,0,0,0,0,0, 0,
                    0,0,0,0,0,0,0, 0, 0);
            String[] lore = {
                    ChatColor.GRAY+"Damage taken under "+ChatColor.GREEN+"150"+ChatColor.GRAY+" is ",
                    ChatColor.GREEN+"halved"+ChatColor.GRAY+"."
            };
            String[] lore2 = {
                    ChatColor.GRAY+"Upon landing a hit, create a",
                    ChatColor.GRAY+"massive explosion, dealing "+ChatColor.GREEN+"25%",
                    ChatColor.GRAY+"of damage to all hit enemies."
            };
            List<Ability> abilities = new ArrayList<>();
            Ability armageddon = new Ability(Arrays.asList(lore), "Armageddon", 0,0, Ability.Action.PASSIVE, Ability.Type.SINGLE_ABILITY);
            Ability fullSet = new Ability(Arrays.asList(lore2), "Totality", 0,0, Ability.Action.PASSIVE, Ability.Type.SET_ABILITY);
            abilities.add(armageddon);
            abilities.add(fullSet);
            ItemBase item = new ItemBase(Rarity.LEGENDARY, "Armageddon", Material.GOLDEN_AXE, ItemType.SHIELD, abilities, stats);
            item.setSet("ragnarok");
            this.item = item;
        }
    },
    ;
    public ItemBase item;
    public ItemBase getItem() {
        return item;
    }
    public static void setContained() {
        for (OffHands s: OffHands.values()) {
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
