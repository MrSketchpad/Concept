package com.sketchpad.concept.utilities.items;

import com.sketchpad.concept.items.*;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    String set = "";
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
    public static ItemBase fromItemStack(ItemStack i) {
        boolean getSpecific = i.hasItemMeta() && !i.getItemMeta().getPersistentDataContainer().isEmpty();
        String[] words = i.getType().toString().replace("_", " ").toLowerCase().split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        String name = capitalizeWord.toString().trim();
        ItemBase item = new ItemBase(Rarity.COMMON, name, i.getType(), ItemType.MATERIAL);
        if (getSpecific && Objects.equals(NbtManager.getNbt(i, PersistentDataType.STRING, "type"), "inventory")) item = new ItemBase(Rarity.COMMON, name, i.getType(), ItemType.INVENTORY);
        for (Materials m:Materials.values()) {
            if (m.equals(i)) {
                item = m.getItem();
            }
        }
        for (Bow b:Bow.values()) {
            if (b.equals(i)) {
                item = b.getItem();
            }
        }
        for (Sword sw: Sword.values()) {
            if (sw.equals(i)) {
                item = sw.getItem();
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 0)) {
                item = ar.getItems().get(0);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 1)) {
                item = ar.getItems().get(1);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 2)) {
                item = ar.getItems().get(2);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 3)) {
                item = ar.getItems().get(3);
                break;
            }
        }
        for (Reforges ar:Reforges.values()) {
            if (ar.equals(i)) {
                item = ar.getReforge().toBaseItem();
                break;
            }
        }
        for (OffHands f:OffHands.values()) {
            if (f.equals(i)) {
                item = f.getItem();
                break;
            }
        }
        return item;
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

    public void setSet(String set) {
        this.set = set;
    }
    public String getSet() {
        return set;
    }
}
