package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum Armor implements ItemShell {
    SKETCHPAD {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setHealth(1000);
            stats.setDefense(1000);
            stats.setIntelligence(1000);

            Ability ab = new Ability(List.of(
                    "Become invincible to all damage!"
            ), "Bringer of Life", 0,0, Ability.Action.PASSIVE, Ability.Type.FULL_SET);

            helmet = new ItemBase(Rarity.MYTHIC, "Sketchpad's Helmet", Material.GOLDEN_HELMET, ItemType.HELMET, stats);
            helmet.setAbilities(List.of(ab));
            chestplate = new ItemBase(Rarity.MYTHIC, "Sketchpad's Chestplate", Material.GOLDEN_CHESTPLATE, ItemType.CHESTPLATE, stats);
            chestplate.setAbilities(List.of(ab));
            leggings = new ItemBase(Rarity.MYTHIC, "Sketchpad's Leggings", Material.GOLDEN_LEGGINGS, ItemType.LEGGINGS, stats);
            leggings.setAbilities(List.of(ab));
            boots = new ItemBase(Rarity.MYTHIC, "Sketchpad's Boots", Material.GOLDEN_BOOTS, ItemType.BOOTS, stats);
            boots.setAbilities(List.of(ab));
        }
    },
    TEST {
        @Override
        public void setObject() {
            SkyblockStats skyblockStats = new SkyblockStats(2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647, 2147483647,
                    2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,1, 2147483647,
                    2147483647);

            helmet = new ItemBase(Rarity.LEGENDARY, "Helmet Of The Test", Material.DIAMOND_HELMET, ItemType.HELMET, skyblockStats);
            chestplate = new ItemBase(Rarity.LEGENDARY, "Chestplate Of The Test", Material.DIAMOND_CHESTPLATE, ItemType.CHESTPLATE, skyblockStats);
            leggings = new ItemBase(Rarity.LEGENDARY, "Leggings Of The Test", Material.DIAMOND_LEGGINGS, ItemType.LEGGINGS, skyblockStats);
            boots = new ItemBase(Rarity.LEGENDARY, "Boots Of The Test", Material.DIAMOND_BOOTS, ItemType.BOOTS, skyblockStats);
        }
    },
    SUPERIOR_DRAGON {
        @Override
        public void setObject() {
            SkyblockStats helmetStats = new SkyblockStats(90,25,130,0,0,0,0,0,2,
                    10, 0,0,0,0,10,0,3,0, 0, 0);
            helmet = new ItemBase(Rarity.LEGENDARY, "Superior Dragon Helmet", Material.PLAYER_HEAD, ItemType.HELMET, helmetStats);
            helmet.setUrl("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNz" +
                    "U1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=");
            SkyblockStats chestplateStats = new SkyblockStats(150,25,190,0,0,0,0,0,2,
                    10, 0,0,0,0,10,0,3,0, 0, 0);
            chestplate = new ItemBase(Rarity.LEGENDARY, "Superior Dragon Chestplate", Material.LEATHER_CHESTPLATE, ItemType.CHESTPLATE, chestplateStats);
            chestplate.setDye(Color.fromRGB(242, 223, 17));
            SkyblockStats leggingsStats = new SkyblockStats(130,25,170,0,0,0,0,0,2,
                    10, 0,0,0,0,10,0,3,0, 0, 0);
            leggings = new ItemBase(Rarity.LEGENDARY, "Superior Dragon Leggings", Material.LEATHER_LEGGINGS, ItemType.LEGGINGS, leggingsStats);
            leggings.setDye(Color.fromRGB(242, 223, 17));
            SkyblockStats bootsStats = new SkyblockStats(80,25,110,0,0,0,0,0,2,
                    10, 0,0,0,0,10,0,3,0, 0, 0);
            boots = new ItemBase(Rarity.LEGENDARY, "Superior Dragon Boots", Material.LEATHER_BOOTS, ItemType.BOOTS, bootsStats);
            boots.setDye(Color.fromRGB(242, 93, 24));
            Ability ab = new Ability(List.of(
                    "All of your stats are increased by",
                    c.green("5%")+" and "+ c.gold("Aspect of the"),
                    c.gold("Dragons")+" ability deals "+c.green("+50%"),
                    "more damage."
            ), "Superior Blood", 0,0, Ability.Action.PASSIVE, Ability.Type.FULL_SET);
            List<Ability> abilities = helmet.getAbilities();
            abilities.add(ab);

            helmet.setAbilities(abilities);
            chestplate.setAbilities(abilities);
            leggings.setAbilities(abilities);
            boots.setAbilities(abilities);

        }
    },
    ;
    public ItemBase helmet = new ItemBase(Rarity.ERROR, "No Helmet Assigned", Material.LEATHER_HELMET, ItemType.HELMET);
    public ItemBase chestplate = new ItemBase(Rarity.ERROR, "No Chestplate Assigned", Material.LEATHER_CHESTPLATE, ItemType.CHESTPLATE);
    public ItemBase leggings = new ItemBase(Rarity.ERROR, "No Leggings Assigned", Material.LEATHER_LEGGINGS, ItemType.LEGGINGS);
    public ItemBase boots = new ItemBase(Rarity.ERROR, "No Boots Assigned", Material.LEATHER_BOOTS, ItemType.BOOTS);
    public List<ItemBase> getItems() {
        List<ItemBase> items = new ArrayList<>();
        items.add(helmet);
        items.add(chestplate);
        items.add(leggings);
        items.add(boots);
        return items;
    }
    public static void setContained() {
        for (Armor s:Armor.values()) {
            s.setObject();
        }
    }
    public boolean equals(@Nullable ItemStack i, int index) {
        if (i!=null) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("name"), PersistentDataType.STRING)) {
                    return Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING), getItems().get(index).getDisplayName());
                }
            }
        }
        return false;
    }
    public boolean equals(@Nullable ItemBase i, int index) {
        if (i!=null) {
            return i.getDisplayName().equals( getItems().get(index).getDisplayName());
        }
        return false;
    }
}
