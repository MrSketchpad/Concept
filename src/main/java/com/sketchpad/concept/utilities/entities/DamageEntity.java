package com.sketchpad.concept.utilities.entities;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.events.PlayerDamageMobEvent;
import com.sketchpad.concept.items.OffHands;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.damage.DamageCalculator;
import com.sketchpad.concept.utilities.damage.DisplayDamage;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class DamageEntity {
    public static void melee(Player p, LivingEntity en, double damage, boolean setAbility) {
        SkyblockStats stats = GetStats.getPlayer(p);
        Random rand = new Random();
        double dmg = DamageCalculator.melee(p, en);
        boolean crit = rand.nextInt(100)<stats.getCritChance();
        if (crit) dmg*=(1+(stats.getCritDamage()/100));
        if (damage>0) {
            dmg = damage;
            crit = false;
        }

        double newFero = stats.getFerocity();
        int hitNumber = 1;
        while (rand.nextInt(100) < newFero) {
            newFero -= 100;
            hitNumber += 1;
        }
        final int[] i = {0};
        int finalHitNumber = hitNumber;
        SkyblockItem weapon = new SkyblockItem(new ItemBase(Rarity.COMMON, "", Material.STONE, ItemType.MATERIAL));
        if (p.getInventory().getItemInMainHand().hasItemMeta()) weapon = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
        PlayerDamageMobEvent ev = new PlayerDamageMobEvent(SkyblockMob.fromLivingEntity(en), p, dmg, i[0], crit, weapon, en);
        Bukkit.getPluginManager().callEvent(ev);
        dmg = ev.getDamage();
        i[0] = ev.getHits();
        ev.setSetAbility(setAbility);
        boolean finalCrit = ev.isCrit();
        if (!ev.isCancelled()) {
            double finalDmg = dmg;
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (i[0]< finalHitNumber) {
                        if (en.getHealth()<=0) {
                            cancel();
                        }
                        i[0]++;
                        if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInOffHand().hasItemMeta()) {
                            SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                            SkyblockItem f = SkyblockItem.fromItemStack(p.getInventory().getItemInOffHand());
                            if (i.isFullSet(f.getBase())) {
                                p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1);
                                List<Entity> entities = p.getNearbyEntities(5,2,5);
                                if (!entities.isEmpty()) {
                                    for (Entity en:entities) {
                                        if (en instanceof LivingEntity e && en.getType() != EntityType.ARMOR_STAND) {
                                            DisplayDamage.melee(e, finalDmg /4, finalCrit);
                                            UpdateEntity.setNbt(e, finalDmg /4, p);
                                            UpdateDisplayHealth.update(e, p);
                                            e.damage(0);
                                        }
                                    }
                                }
                            }

                        }
                        DisplayDamage.melee(en, finalDmg, finalCrit);
                        UpdateEntity.setNbt(en, finalDmg, p);
                        UpdateDisplayHealth.update(en, p);
                        if (finalHitNumber > 1) {
                            en.damage(0);
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
}
