package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public enum Materials implements ItemShell {
    RECOMBOBULATOR {
        @Override
        public void setObject() {
            String[] lore = {
                    ChatColor.GRAY+"Use in a "+ChatColor.GREEN+"Reforge Anvil"+ChatColor.GRAY+" or at",
                    ChatColor.GRAY+"the Dungeon Blacksmith to",
                    ChatColor.GRAY+"permanently increase the rarity",
                    ChatColor.GRAY+"of an item. An item's rarity can",
                    ChatColor.GRAY+"only be upgraded once!",
            };
            this.item = new ItemBase(Rarity.LEGENDARY, "Recombobulator 3000", Material.PLAYER_HEAD, ItemType.UPGRADEABLE);
            this.item.setLore(Arrays.asList(lore));
            this.item.setUrl("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0" +
                    "dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjY2QzNmRjOGY3MmFkY2IxZjhjOGU2MWVlODJjZDk2ZWFkMTQwY2YyYTE2YTEzNjZiZTliNWE4ZTNjYzNmYyJ9fX0=");
        }
    },
    HOT_POTATO_BOOK {
        @Override
        public void setObject() {
            String[] lore = {
                    ChatColor.GRAY+"Combine this Book in an Anvil",
                    ChatColor.GRAY+"with a weapon or armor piece to",
                    ChatColor.GRAY+"gain a small but permanent stat",
                    ChatColor.GRAY+"boost!",
            };
            this.item = new ItemBase(Rarity.EPIC, "Hot Potato Book", Material.BOOK, ItemType.UPGRADEABLE);
            this.item.setLore(Arrays.asList(lore));
        }
    },
    FUMING_POTATO_BOOK {
        @Override
        public void setObject() {
            String[] lore = {
                    ChatColor.GRAY+"Use in an anvil or "+ChatColor.GREEN+"Reforge",
                    ChatColor.GREEN+"Anvil"+ChatColor.GRAY+" to combine this book",
                    ChatColor.GRAY+"with a weapon or armor piece to",
                    ChatColor.GRAY+"gain a small but permanent stat",
                    ChatColor.GRAY+"boost!",
                    "",
                    ChatColor.GRAY+"This book bypasses the Hot",
                    ChatColor.GRAY+"Potato Book limit of 10,",
                    ChatColor.GRAY+"allowing you to upgrade an item",
                    ChatColor.GRAY+"up to "+ChatColor.GREEN+"15"+ChatColor.GRAY+" times!",
            };
            this.item = new ItemBase(Rarity.EPIC, "Fuming Potato Book", Material.BOOK, ItemType.UPGRADEABLE);
            this.item.setLore(Arrays.asList(lore));
        }
    },
    ;
    public ItemBase item;
    public ItemBase getItem() {
        return item;
    }
    public static void setContained() {
        for (Materials s: Materials.values()) {
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
