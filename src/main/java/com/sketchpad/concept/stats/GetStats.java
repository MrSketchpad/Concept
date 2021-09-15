package com.sketchpad.concept.stats;

import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.player.ArmorUtilities;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("ConstantConditions")
public class GetStats {
    public static HashMap<UUID, Double> bonusIntelligence = new HashMap<>();
    public static @NotNull
    SkyblockStats getPlayer(@NotNull Player p) {
        SkyblockStats skyblockStats = SkyblockStats.getPlayer();
        if (p.getEquipment().getHelmet()!=null && p.getEquipment().getHelmet().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getEquipment().getHelmet(), ItemType.HELMET));
        if (p.getEquipment().getChestplate()!=null && p.getEquipment().getChestplate().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getEquipment().getChestplate(), ItemType.CHESTPLATE));
        if (p.getEquipment().getLeggings()!=null && p.getEquipment().getLeggings().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getEquipment().getLeggings(), ItemType.LEGGINGS));
        if (p.getEquipment().getBoots()!=null && p.getEquipment().getBoots().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getEquipment().getBoots(), ItemType.BOOTS));
        if (p.getEquipment().getItemInMainHand().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(p.getEquipment().getItemInMainHand(), ItemType.MAIN_HAND));
        if (bonusIntelligence.containsKey(p.getUniqueId())) skyblockStats.setIntelligence(skyblockStats.getIntelligence()+bonusIntelligence.get(p.getUniqueId()));
        if (ArmorUtilities.hasFullSet(p, Armor.SUPERIOR_DRAGON)) {
            skyblockStats.setTotalModifier(skyblockStats.getTotalModifier()+0.05);
        }
        return skyblockStats.multiply(skyblockStats.getTotalModifier());
    }
    public static @NotNull
    SkyblockStats getEntity(@NotNull LivingEntity en) {
        SkyblockStats skyblockStats = SkyblockStats.getPlayer();
        if (en.getEquipment().getHelmet()!=null && en.getEquipment().getHelmet().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(en.getEquipment().getHelmet(), ItemType.HELMET));
        if (en.getEquipment().getChestplate()!=null && en.getEquipment().getChestplate().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(en.getEquipment().getChestplate(), ItemType.CHESTPLATE));
        if (en.getEquipment().getLeggings()!=null && en.getEquipment().getLeggings().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(en.getEquipment().getLeggings(), ItemType.LEGGINGS));
        if (en.getEquipment().getBoots()!=null && en.getEquipment().getBoots().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(en.getEquipment().getBoots(), ItemType.BOOTS));
        if (en.getEquipment().getItemInMainHand().hasItemMeta()) skyblockStats =
                skyblockStats.add(NbtManager.getAll(en.getEquipment().getItemInMainHand(), ItemType.MAIN_HAND));
        if (ArmorUtilities.hasFullSet(en, Armor.SUPERIOR_DRAGON)) {
            skyblockStats.setTotalModifier(skyblockStats.getTotalModifier()+0.05);
        }
        return skyblockStats.multiply(skyblockStats.getTotalModifier());
    }
}
