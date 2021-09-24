package com.sketchpad.concept.utilities.reforges;

import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Reforge {
    SkyblockStats common;
    SkyblockStats uncommon;
    SkyblockStats rare;
    SkyblockStats epic;
    SkyblockStats legendary;
    SkyblockStats mythic;
    ItemType type;
    String name;
    Rarity rarity;
    Material material;
    int miningRequirement;
    List<String> ability;
    Rarity minimum;
    public Reforge(SkyblockStats common, SkyblockStats uncommon, SkyblockStats rare,
                   SkyblockStats epic, SkyblockStats legendary, SkyblockStats mythic,
                   ItemType type, String name, int miningRequirement,
                   Rarity rarity, Material material, List<String> ability, Rarity minimum) {
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;
        this.epic = epic;
        this.legendary = legendary;
        this.mythic = mythic;
        this.type = type;
        this.name = name;
        this.miningRequirement = miningRequirement;
        this.rarity = rarity;
        this.material = material;
        this.ability = ability;
        this.minimum = minimum;
    }
    public ItemBase toBaseItem() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Reforge Stone");
        lore.add("");
        lore.add(ChatColor.GRAY+"Can be used in a Reforge Anvil");
        lore.add(ChatColor.GRAY+"or with the Dungeon Blacksmith");
        lore.add(ChatColor.GRAY+"to apply the "+ChatColor.BLUE+name+ChatColor.GRAY+" reforge");
        lore.add(ChatColor.GRAY+"to "+type.name().toLowerCase()+".");
        lore.add("");
        lore.add(ChatColor.GRAY+"Requires "+ChatColor.GREEN+"Mining Skill Level");
        lore.add(ChatColor.GREEN+""+ NumberUtilities.toRomanNumeral(miningRequirement) +ChatColor.GRAY+"!");
        return new ItemBase(rarity, name, material, ItemType.REFORGE_STONE, SkyblockStats.getEmpty(), lore);
    }
    public SkyblockStats getStats(Rarity r) {
        switch (r) {
            case COMMON -> {
                return common;
            }
            case UNCOMMON -> {
                return uncommon;
            }
            case RARE -> {
                return rare;
            }
            case EPIC -> {
                return epic;
            }
            case LEGENDARY -> {
                return legendary;
            }
            case MYTHIC, VERY_SPECIAL, SPECIAL -> {
                return mythic;
            }
        }
        return SkyblockStats.getEmpty();
    }
    public ItemType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public Material getMaterial() {
        return material;
    }
    public int getMiningRequirement() {
        return miningRequirement;
    }
    public List<String> getAbility() {
        return ability;
    }
    public void setAbility(List<String> ability) {
        this.ability = ability;
    }
}
