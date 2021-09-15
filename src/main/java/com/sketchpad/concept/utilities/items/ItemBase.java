package com.sketchpad.concept.utilities.items;

import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import org.bukkit.Color;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemBase {
    Rarity rarity;
    String displayName;
    Material material;
    ItemType type;
    SkyblockStats skyblockStats = SkyblockStats.getEmpty();
    List<String> lore  = new ArrayList<>();
    List<Ability> abilities = new ArrayList<>();
    String url = "";
    Color dye = Color.fromRGB(160, 101, 64);
    Enchant inventoryEnchant = Enchant.FILL;
    int inventoryEnchLevel = 0;
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type) {
        abilities.add(new Ability(new ArrayList<>(), "", 0,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY));
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
    }
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type, @NotNull SkyblockStats skyblockStats) {
        abilities.add(new Ability(new ArrayList<>(), "", 0,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY));
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
        this.skyblockStats = skyblockStats;
    }
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type,
                    @NotNull List<Ability> abilities, @NotNull SkyblockStats skyblockStats) {
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
        this.skyblockStats = skyblockStats;
        this.abilities = abilities;
    }
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type,
                    @NotNull Ability ability, @NotNull SkyblockStats skyblockStats) {
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
        this.skyblockStats = skyblockStats;
        this.abilities.add(ability);
    }
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type, @NotNull SkyblockStats skyblockStats,
                    @NotNull List<String> lore) {
        abilities.add(new Ability(new ArrayList<>(), "", 0,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY));
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
        this.skyblockStats = skyblockStats;
        this.lore = lore;
    }
    public ItemBase(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull ItemType type, @NotNull SkyblockStats skyblockStats,
                    @NotNull List<String> lore, @NotNull List<Ability> abilities) {
        this.rarity = rarity;
        this.displayName = displayName;
        this.material = material;
        this.type = type;
        this.skyblockStats = skyblockStats;
        this.lore = lore;
        this.abilities = abilities;
    }
    public String getDisplayName() {
        return displayName;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public boolean hasStats() {
        return (!skyblockStats.equals(SkyblockStats.getEmpty())) ;
    }
    public void setStats(SkyblockStats skyblockStats) {
        this.skyblockStats = skyblockStats;
    }
    public SkyblockStats getStats() {
        return skyblockStats;
    }
    public ItemType getType() {
        return type;
    }
    public void setType(ItemType type) {
        this.type = type;
    }
    public List<Ability> getAbilities() {
        return abilities;
    }
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
    public List<String> getLore() {
        return lore;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public Color getDye() {
        return dye;
    }
    public void setDye(Color dye) {
        this.dye = dye;
    }
    public Enchant getInventoryEnchant() {
        return inventoryEnchant;
    }
    public void setInventoryEnchant(Enchant inventoryEnchant) {
        this.inventoryEnchant = inventoryEnchant;
    }
    public void setInventoryEnchLevel(int inventoryEnchLevel) {
        this.inventoryEnchLevel = inventoryEnchLevel;
    }
    public int getInventoryEnchLevel() {
        return inventoryEnchLevel;
    }
}
