package com.sketchpad.concept.utilities.entities;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.damage.DamageCalculator;
import com.sketchpad.concept.utilities.damage.DisplayDamage;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class DamageEntity {
    public static void melee(Player p, LivingEntity en) {
        SkyblockStats stats = GetStats.getPlayer(p);
        Random rand = new Random();
        double dmg = DamageCalculator.melee(p, en);
        boolean crit = rand.nextInt(100)<stats.getCritChance();
        if (crit) dmg*=(1+(stats.getCritDamage()/100));

        double newFero = stats.getFerocity();
        int hitNumber = 1;
        while (rand.nextInt(100) < newFero) {
            newFero -= 100;

            hitNumber += 1;
        }
        final int[] i = {0};
        int finalHitNumber = hitNumber;
        double finalDmg = dmg;

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (i[0]< finalHitNumber) {
                    i[0]++;
                    UpdateDisplayHealth.update(en, p);
                    DisplayDamage.melee(en, finalDmg, crit);
                    UpdateEntity.setNbt(en, finalDmg, p);
                    if (finalHitNumber > 1) {
                        int chance = rand.nextInt(2);
                        if (chance == 0) {
                            p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 2, 1);
                        } else {
                            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.2F, 2);
                        }
                    }
                } else cancel();
            }
        };
        runnable.runTaskTimer(Concept.instance, 0, 2);
    }
}
