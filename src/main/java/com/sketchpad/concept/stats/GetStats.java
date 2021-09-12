package com.sketchpad.concept.stats;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.player.ArmorUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class GetStats {
    public static HashMap<UUID, Double> bonusIntelligence = new HashMap<>();
    public static @NotNull
    SkyblockStats getPlayer(@NotNull Player p) {
        SkyblockStats skyblockStats = SkyblockStats.getPlayer();
        if (p.getInventory().getHelmet()!=null && p.getInventory().getHelmet().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getInventory().getHelmet(), ItemType.HELMET));
        if (p.getInventory().getChestplate()!=null && p.getInventory().getChestplate().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getInventory().getChestplate(), ItemType.CHESTPLATE));
        if (p.getInventory().getLeggings()!=null && p.getInventory().getLeggings().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getInventory().getLeggings(), ItemType.LEGGINGS));
        if (p.getInventory().getBoots()!=null && p.getInventory().getBoots().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getInventory().getBoots(), ItemType.BOOTS));
        if (p.getInventory().getItemInMainHand().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getInventory().getItemInMainHand(), ItemType.MAIN_HAND));
        if (bonusIntelligence.containsKey(p.getUniqueId())) skyblockStats.setIntelligence(skyblockStats.getIntelligence()+bonusIntelligence.get(p.getUniqueId()));
        if (ArmorUtilities.hasFullSet(p, Armor.SUPERIOR_DRAGON)) {
            skyblockStats.setTotalModifier(skyblockStats.getTotalModifier()+0.05);
        }
        return skyblockStats.multiply(skyblockStats.getTotalModifier());
    }
}
