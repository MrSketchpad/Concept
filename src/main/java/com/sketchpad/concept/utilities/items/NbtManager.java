package com.sketchpad.concept.utilities.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.SkyblockStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class NbtManager {
    public static void addNbt(@NotNull ItemStack i, @NotNull PersistentDataType type, @NotNull String name, @NotNull Object value) {
        ItemMeta m = i.getItemMeta();
        m.getPersistentDataContainer().set(Concept.getKey(name), type, value);
        i.setItemMeta(m);
    }
    public static @Nullable
    Object getNbt(@NotNull ItemStack i, @NotNull PersistentDataType type, @NotNull String name) {
        ItemMeta m = i.getItemMeta();
        if (m.getPersistentDataContainer().has(Concept.getKey(name), type)) {
            return m.getPersistentDataContainer().get(Concept.getKey(name), type);
        }
        return null;
    }
    public static boolean hasNbt(@NotNull ItemStack i, @NotNull PersistentDataType type, @NotNull String name) {
        ItemMeta m = i.getItemMeta();
        return m.getPersistentDataContainer().has(Concept.getKey(name), type);
    }
    public static @NotNull
    SkyblockStats getAll(@NotNull ItemStack i, ItemType type) {
        if (type.getTypes().contains(ItemType.fromString((String) getNbt(i, PersistentDataType.STRING, "type")))) {
            double health = (double) getNbt(i, PersistentDataType.DOUBLE, "health");
            double intelligence = (double) getNbt(i, PersistentDataType.DOUBLE, "intelligence");
            double defense = (double) getNbt(i, PersistentDataType.DOUBLE, "defense");
            double damage = (double) getNbt(i, PersistentDataType.DOUBLE, "damage");
            double strength = (double) getNbt(i, PersistentDataType.DOUBLE, "strength");
            double ferocity = (double) getNbt(i, PersistentDataType.DOUBLE, "ferocity");
            double petLuck = (double) getNbt(i, PersistentDataType.DOUBLE, "petLuck");
            double magicFind = (double) getNbt(i, PersistentDataType.DOUBLE, "magicFind");
            double seaCreatureChance = (double) getNbt(i, PersistentDataType.DOUBLE, "seaCreatureChance");
            double trueDefense = (double) getNbt(i, PersistentDataType.DOUBLE, "trueDefense");
            double critChance = (double) getNbt(i, PersistentDataType.DOUBLE, "critChance");
            double critDamage = (double) getNbt(i, PersistentDataType.DOUBLE, "critDamage");
            double abilityDamage = (double) getNbt(i, PersistentDataType.DOUBLE, "abilityDamage");
            double miningFortune = (double) getNbt(i, PersistentDataType.DOUBLE, "miningFortune");
            double farmingFortune = (double) getNbt(i, PersistentDataType.DOUBLE, "farmingFortune");
            double foragingFortune = (double) getNbt(i, PersistentDataType.DOUBLE, "foragingFortune");
            double speed = (double) getNbt(i, PersistentDataType.DOUBLE, "speed");
            double totalModifier = (double) getNbt(i, PersistentDataType.DOUBLE, "total");
            return new SkyblockStats(health, intelligence, defense, ferocity, petLuck, magicFind, seaCreatureChance, trueDefense, critChance, critDamage, abilityDamage,
                    miningFortune, farmingFortune, foragingFortune, strength, damage, speed, totalModifier);
        }
        return SkyblockStats.getEmpty();
    }
    public static void addAll(@NotNull ItemStack i, @NotNull SkyblockStats skyblockStats) {
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "health", skyblockStats.getHealth());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "intelligence", skyblockStats.getIntelligence());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "defense", skyblockStats.getDefense());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "damage", skyblockStats.getDamage());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "strength", skyblockStats.getStrength());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "ferocity", skyblockStats.getFerocity());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "petLuck", skyblockStats.getPetLuck());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "magicFind", skyblockStats.getMagicFind());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "seaCreatureChance", skyblockStats.getSeaCreatureChance());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "trueDefense", skyblockStats.getTrueDefense());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "critChance", skyblockStats.getCritChance());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "critDamage", skyblockStats.getCritDamage());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "abilityDamage", skyblockStats.getAbiilityDamage());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "miningFortune", skyblockStats.getMiningFortune());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "farmingFortune", skyblockStats.getFarmingFortune());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "foragingFortune", skyblockStats.getForagingFortune());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "speed", skyblockStats.getSpeed());
        NbtManager.addNbt(i, PersistentDataType.DOUBLE, "total", skyblockStats.getTotalModifier());
    }
}
