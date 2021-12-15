package com.sketchpad.concept;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum MySwords {
    AN_ITEM {
        @Override
        public ItemStack getItem() {
            ItemStack myItem = new ItemStack(Material.DIAMOND);
            // Customize item here
            myItem.getItemMeta().setDisplayName("Weee!");
            // return item
            return myItem;
        }
    },
    ANOTHER_ITEM {
        @Override
        public ItemStack getItem() {
            ItemStack myItem = new ItemStack(Material.STONE);
            // Customize item here
            myItem.getItemMeta().setDisplayName("Weee2!");
            // return item
            return myItem;
        }
    }
    ;
    public ItemStack getItem() {
        return null;
    }
}
