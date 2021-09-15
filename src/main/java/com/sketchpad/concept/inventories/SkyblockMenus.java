package com.sketchpad.concept.inventories;

import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.formatting.Number;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkyblockMenus {
    public enum ReforgeStatus {
        LEFT_SUCCESS,
        RIGHT_SUCCESS,
        SUCCESS,
        FAIL,
        ;

    }
    public static @NotNull SkyblockInventory chooseEnchantLevelMenu(@NotNull SkyblockEnchants enchants, @NotNull Enchant en) {
        SkyblockInventory inv = new SkyblockInventory(54, "Choose Level", true);
        for (int i = 1; i<en.getMaxValue()+1; i++) {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.BLUE + en.getName() + " " + Number.toRomanNumeral(i));
            lore.addAll(en.getDesc(i));
            lore.add("");
            String enchantLore = ChatColor.YELLOW+"Click to enchant!";
            if (enchants.enchants.containsKey(en) && enchants.enchants.get(en) != null
                    && enchants.enchants.get(en) > 0) {
                lore.add(ChatColor.RED + "This enchantment is already");
                lore.add(ChatColor.RED + "present.");
                lore.add("");
                enchantLore = ChatColor.YELLOW + "Click to remove!";
            } else {
                for (Enchant e : en.getConflicting()) {
                    if (enchants.enchants.containsKey(e) && enchants.enchants.get(e) != null &&
                            enchants.enchants.get(e) > 0) {
                        lore.add(ChatColor.RED+""+ChatColor.BOLD+"WARNING: This will remove");
                        lore.add(ChatColor.RED+""+ChatColor.BOLD+e.getName()+".");
                        lore.add("");
                        enchantLore = ChatColor.YELLOW + "Click to replace!";
                    }
                }
            }
            lore.add(enchantLore);
            String name = ChatColor.WHITE+"Enchanted Book";
            if (i>=5) name = ChatColor.GREEN+"Enchanted Book";
            ItemBase it = new ItemBase(Rarity.COMMON, name, Material.ENCHANTED_BOOK,
                    ItemType.INVENTORY, SkyblockStats.getEmpty(), lore);
            it.setInventoryEnchant(en);
            it.setInventoryEnchLevel(i);
            inv.setItem((21-((en.getMaxValue()-1)/2))+(i), new SkyblockItem(it));
        }
        inv.setItem(49, new SkyblockItem(InventoryItems.close()));
        inv.setItem(48, new SkyblockItem(InventoryItems.goBack()));
        return inv;
    }
    public static @NotNull SkyblockInventory enchantmentMenu(@Nullable ItemType i) {
        SkyblockInventory inv = new SkyblockInventory(54, "Enchant Item", true);
        if (i!=null) {
            inv.clear(12);
            inv.clear(13);
            inv.clear(14);
            inv.clear(15);
            inv.clear(16);
            inv.clear(21);
            inv.clear(22);
            inv.clear(23);
            inv.clear(24);
            inv.clear(25);
            inv.clear(30);
            inv.clear(31);
            inv.clear(32);
            inv.clear(33);
            inv.clear(34);
        } else inv.setItem(23, new SkyblockItem(InventoryItems.enchantItemMissing()));
        inv.setItem(28, new SkyblockItem(InventoryItems.enchantItem()));

        if (i!=null) {
            for (Enchant en: SkyblockEnchants.getType(i, 1).enchants.keySet()) {
                List<String> lore = new ArrayList<>(en.getDesc(1));
                lore.add("");
                lore.add(ChatColor.YELLOW+"Click to view!");
                ItemBase base = new ItemBase(Rarity.COMMON, ChatColor.GREEN+en.getName(), Material.ENCHANTED_BOOK,
                        ItemType.INVENTORY, SkyblockStats.getEmpty(), lore);
                base.setInventoryEnchant(en);
                inv.addToNearestEmpty(new SkyblockItem(base));
            }
        }
        inv.clear(19);
        inv.setItem(49, new SkyblockItem(InventoryItems.close()));
        return inv;
    }
    public static @NotNull SkyblockInventory skyblockMenu(Player p) {
        SkyblockInventory inv = new SkyblockInventory(54, "Skyblock Menu", true);
        inv.setItem(13, new SkyblockItem(InventoryItems.stats(p)));
        inv.setItem(22, new SkyblockItem(InventoryItems.reforgeButton()));
        return inv;
    }
    public static @NotNull SkyblockInventory reforgeMenu(ReforgeStatus status) {
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
