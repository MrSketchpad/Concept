package com.sketchpad.concept.utilities.player;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.stats.StatManager;
import com.sketchpad.concept.utilities.damage.DamageCalculator;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.entities.DamageEntity;
import com.sketchpad.concept.utilities.formatting.Number;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class DamagePlayer {
    public static void kill(Player p, String cause) {
        p.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
        SkyblockStats stats = GetStats.getPlayer(p);

        if (!cause.equals("")) Bukkit.broadcastMessage(ChatColor.GRAY+p.getDisplayName()+ChatColor.GRAY+" was killed by "+cause+"!");
        else Bukkit.broadcastMessage(ChatColor.GRAY+p.getDisplayName()+ChatColor.GRAY+" died!");

        p.sendMessage(ChatColor.RED+"☠"+ChatColor.GRAY+" You died.");
        p.sendMessage(ChatColor.RED+"You died and lost "+ Number.addCommas(BigDecimal.valueOf(0))+" coins!");
        StatManager.playerHealths.put(p.getUniqueId(), stats.getHealth());
        StatManager.playerMana.put(p.getUniqueId(), stats.getIntelligence());
        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
        p.setHealth(p.getMaxHealth());
    }
    public static void melee(Player p, LivingEntity en) {
        Concept.inCombat.put(p.getUniqueId(), 0);
        double dmg = DamageCalculator.entity(en, p);
        StatManager.playerHealths.put(p.getUniqueId(), StatManager.playerHealths.get(p.getUniqueId())-dmg);
        StatManager.runOnce(p);
        if (new Random().nextBoolean()) {
            if (SkyblockEnchants.getAll(p).enchants.containsKey(Enchant.THORNS) && SkyblockEnchants.getAll(p).enchants.get(Enchant.THORNS)!=null
                    && SkyblockEnchants.getAll(p).enchants.get(Enchant.THORNS)>0) {
                double newDmg = dmg/100;
                newDmg *= 3*SkyblockEnchants.getAll(p).enchants.get(Enchant.THORNS);
                DamageEntity.melee(p, en, newDmg);
            }
        }
    }
}
