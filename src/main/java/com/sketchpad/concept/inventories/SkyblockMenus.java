package com.sketchpad.concept.inventories;

import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SkyblockMenus {
    public enum ReforgeStatus {
        LEFT_SUCCESS,
        RIGHT_SUCCESS,
        SUCCESS,
        FAIL,
        ;

    }
    public static @NotNull SkyblockInventory skyblockMenu(Player p) {
        SkyblockInventory inv = new SkyblockInventory(54, "Skyblock Menu", true);
        inv.setItem(13, new SkyblockItem(InventoryItems.stats(p)));
        inv.setItem(22, new SkyblockItem(InventoryItems.reforgeButton()));
        return inv;
    }
    public static @NotNull SkyblockInventory reforgeMenu(Player p, ReforgeStatus status) {
        SkyblockInventory inv = new SkyblockInventory(54, "Anvil", true);
        boolean rightStatus = false;
        boolean leftStatus = false;
        InventoryItems.Status success = InventoryItems.Status.FAIL;
        switch (status) {
            case LEFT_SUCCESS -> leftStatus = true;
            case RIGHT_SUCCESS -> rightStatus = true;
            case SUCCESS -> {
                rightStatus = true;
                leftStatus = true;
            }
        }
        if (rightStatus && leftStatus) success = InventoryItems.Status.SUCCESS;
        inv.setItem(11, new SkyblockItem(InventoryItems.leftReforgeItem(leftStatus)));
        inv.setItem(12, new SkyblockItem(InventoryItems.leftReforgeItem(leftStatus)));
        inv.setItem(13, new SkyblockItem(InventoryItems.centerReforgeItem()));
        inv.setItem(14, new SkyblockItem(InventoryItems.rightReforgeItem(rightStatus)));
        inv.setItem(15, new SkyblockItem(InventoryItems.rightReforgeItem(rightStatus)));
        inv.setItem(20, new SkyblockItem(InventoryItems.leftReforgeItem(leftStatus)));
        inv.setItem(22, new SkyblockItem(InventoryItems.combineItemsReforgeItem()));
        inv.setItem(24, new SkyblockItem(InventoryItems.rightReforgeItem(rightStatus)));
        inv.clear(29);
        inv.clear(33);
        inv.setItem(53, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(52, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(51, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(50, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(49, new SkyblockItem(InventoryItems.close()));
        inv.setItem(48, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(47, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(46, new SkyblockItem(InventoryItems.genericStatus(success)));
        inv.setItem(45, new SkyblockItem(InventoryItems.genericStatus(success)));
        return inv;
    }
}
