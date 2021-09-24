package com.sketchpad.concept.items;

import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.items.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InventoryItems {
    public static @NotNull
    ItemBase enchantItemMissing() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Place an item in the open slot");
        lore.add(ChatColor.GRAY+"to enchant it!");
        return new ItemBase(Rarity.COMMON, ChatColor.RED+"Enchant Item", Material.GRAY_DYE, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase enchantItem() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Add an item to the slot above to");
        lore.add(ChatColor.GRAY+"view enchantment options!");
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Enchant Item", Material.ENCHANTING_TABLE, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase goNext() {
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Next", Material.ARROW, ItemType.INVENTORY);
    }
    public static @NotNull
    ItemBase goBack() {
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Go Back", Material.ARROW, ItemType.INVENTORY);
    }
    public static @NotNull
    ItemBase close() {
        return new ItemBase(Rarity.COMMON, ChatColor.RED+"Close", Material.BARRIER, ItemType.INVENTORY);
    }
    public enum Status {
        SUCCESS,
        FAIL,
        MAYBE
        ;
    }
    public static @NotNull
    ItemBase genericStatus(@NotNull Status s) {
        Material m = Material.RED_STAINED_GLASS_PANE;
        if (s==Status.SUCCESS) m = Material.GREEN_STAINED_GLASS_PANE;
        if (s==Status.MAYBE) m = Material.YELLOW_STAINED_GLASS_PANE;
        return new ItemBase(Rarity.COMMON, " ", m, ItemType.INVENTORY);
    }
    public static @NotNull
    ItemBase combineItemsReforgeItem() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Combine the items in the slots");
        lore.add(ChatColor.GRAY+"to the left and right below.");
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Combine Items", Material.ANVIL, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase centerReforgeItem() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Place a target item in the left");
        lore.add(ChatColor.GRAY+"slot and a sacrifice item in the");
        lore.add(ChatColor.GRAY+"right slot to combine upgrades!");
        return new ItemBase(Rarity.COMMON, ChatColor.RED+"Anvil", Material.BARRIER, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase leftReforgeItem(boolean success) {
        List<String> lore = new ArrayList<>();
        Material m = Material.RED_STAINED_GLASS_PANE;
        if (success) m = Material.GREEN_STAINED_GLASS_PANE;
        lore.add(ChatColor.GRAY+"The item you want to upgrade");
        lore.add(ChatColor.GRAY+"should be placed in the slot");
        lore.add(ChatColor.GRAY+"on this side.");
        return new ItemBase(Rarity.COMMON, ChatColor.GOLD+"Item To Upgrade", m, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase rightReforgeItem(boolean success) {
        List<String> lore = new ArrayList<>();
        Material m = Material.RED_STAINED_GLASS_PANE;
        if (success) m = Material.GREEN_STAINED_GLASS_PANE;
        lore.add(ChatColor.GRAY+"The item you are sacrificing in");
        lore.add(ChatColor.GRAY+"order to upgrade the item on the");
        lore.add(ChatColor.GRAY+"left should be placed in the");
        lore.add(ChatColor.GRAY+"slot on this side.");
        return new ItemBase(Rarity.COMMON, ChatColor.GOLD+"Item To Sacrifice", m, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase reforgeButton() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Apply upgrades and reforges");
        lore.add(ChatColor.GRAY+"using the Anvil menu!");
        lore.add("");
        lore.add(ChatColor.YELLOW+"Click to open!");
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Anvil", Material.ANVIL, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase stats(Player p) {
        SkyblockStats stats = GetStats.getPlayer(p);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED+" ❤ Health "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getHealth()), true)+" HP");
        lore.add(ChatColor.GREEN+" ❈ Defense "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getDefense()), true));
        lore.add(ChatColor.RED+" ❁ Strength "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getStrength()), true));
        lore.add(ChatColor.WHITE+" ✦ Speed "+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getSpeed()), true));
        lore.add(ChatColor.BLUE+" ☣ Crit Chance "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getCritChance()), true)+"%");
        lore.add(ChatColor.BLUE+" ☠ Crit Damage "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getCritDamage()), true)+"%");
        lore.add(ChatColor.AQUA+" ✎ Intelligence "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getIntelligence()), true));
        lore.add(ChatColor.GOLD+" ⸕ Mining Speed "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getMiningSpeed()), true));
        lore.add(ChatColor.DARK_AQUA+" α Sea Creature Chance "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getSeaCreatureChance()), true)+"%");
        lore.add(ChatColor.AQUA+" ✯ Magic Find "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getMagicFind()), true));
        lore.add(ChatColor.LIGHT_PURPLE+" ♣ Pet Luck "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getPetLuck()), true));
        lore.add(ChatColor.RED+" ⫽ Ferocity "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getFerocity()), true));
        lore.add(ChatColor.RED+" ๑ Ability Damage "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getAbiilityDamage()), true)+"%");
        lore.add(ChatColor.GOLD+" ☘ Mining Fortune "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getMiningFortune()), true));
        lore.add(ChatColor.GOLD+" ☘ Farming Fortune "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getFarmingFortune()), true));
        lore.add(ChatColor.GOLD+" ☘ Foraging Fortune "+ChatColor.WHITE+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getForagingFortune()), true));
        lore.add("");
        lore.add(ChatColor.YELLOW+"Click to view your profile!");
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Your Skyblock Profile", Material.NETHER_STAR, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase skyblockMenu() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"View all of your Skyblock");
        lore.add(ChatColor.GRAY+"progress, including your Skills,");
        lore.add(ChatColor.GRAY+"Collections, Recipes and more!");
        lore.add("");
        lore.add(ChatColor.YELLOW+"Click to open!");
        return new ItemBase(Rarity.COMMON, ChatColor.GREEN+"Skyblock Menu", Material.NETHER_STAR, ItemType.INVENTORY,
                SkyblockStats.getEmpty(), lore);
    }
    public static @NotNull
    ItemBase menuGlass() {
        return new ItemBase(Rarity.COMMON, " ", Material.GRAY_STAINED_GLASS_PANE, ItemType.INVENTORY);
    }
}
