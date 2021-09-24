package com.sketchpad.concept.utilities.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.items.OffHands;
import com.sketchpad.concept.items.Sword;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.reforges.Reforge;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shortbow extends ItemBase {
    private List<String> shortbowLore = new ArrayList<>();
    private ChatColor color = ChatColor.YELLOW;
    public Shortbow(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material) {
        super(rarity, displayName, material, ItemType.SHORTBOW);
    }

    public Shortbow(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull SkyblockStats skyblockStats) {
        super(rarity, displayName, material, ItemType.SHORTBOW, skyblockStats);
    }

    public Shortbow(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull List<Ability> abilities, @NotNull SkyblockStats skyblockStats) {
        super(rarity, displayName, material, ItemType.SHORTBOW, abilities, skyblockStats);
    }

    public Shortbow(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull Ability ability, @NotNull SkyblockStats skyblockStats) {
        super(rarity, displayName, material, ItemType.SHORTBOW, ability, skyblockStats);
    }

    public Shortbow(@NotNull Rarity rarity, @NotNull String displayName, @NotNull Material material, @NotNull SkyblockStats skyblockStats, @NotNull List<String> lore) {
        super(rarity, displayName, material, ItemType.SHORTBOW, skyblockStats, lore);
    }
    public List<String> getShortbowLore() {
        return shortbowLore;
    }
    public void setShortbowLore(List<String> shortbowLore) {
        this.shortbowLore = shortbowLore;
    }
    public ChatColor getShortbowColor() {
        return color;
    }
    public void setShortbowColor(ChatColor color) {
        this.color = color;
    }
}
