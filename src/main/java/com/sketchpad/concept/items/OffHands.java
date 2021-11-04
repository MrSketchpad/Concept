package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public enum OffHands implements ItemShell {
    ARMAGEDDON {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(375,0,245,0,0,0,0,0,0, 0,
                    0,0,0,0,0,0,0, 0, 0, 0);
            Ability armageddon = new Ability(List.of(
                    "Damage taken under "+c.green("150")+" is",
                    "halved!"
            ), "Armageddon", 0,0, Ability.Action.PASSIVE, Ability.Type.SINGLE_ABILITY);

            Ability fullSet = new Ability(List.of(
                    "Upon landing a critical hit, create a",
                    "massive explosion, dealing "+c.green("25%"),
                    "of damage to all hit enemies."
            ), "Totality", 0,0, Ability.Action.PASSIVE, Ability.Type.SET_ABILITY);

            ItemBase item = new ItemBase(Rarity.LEGENDARY, "Armageddon", Material.GOLDEN_AXE, ItemType.SHIELD, List.of(
                    armageddon, fullSet
            ), stats);
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
