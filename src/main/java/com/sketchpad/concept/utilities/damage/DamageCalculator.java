package com.sketchpad.concept.utilities.damage;

import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.entities.SkyblockMob;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DamageCalculator {
    public static double melee(@NotNull Player p, @Nullable LivingEntity en) {
        SkyblockStats skyblockStats = GetStats.getPlayer(p);
        assert en != null;
        SkyblockMob mob = SkyblockMob.fromLivingEntity(en);
        double damage = (skyblockStats.getDamage()+5)*(1+(skyblockStats.getStrength()/100))*5;
        if (p.getInventory().getItemInMainHand().hasItemMeta()) {
            SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
            damage*=i.getEnchants().getDamageBonus(mob);
        }
        return damage;
    }
}
