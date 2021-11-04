package com.sketchpad.concept.handlers;

import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.utilities.entities.DamageEntity;
import com.sketchpad.concept.utilities.player.ArmorUtilities;
import com.sketchpad.concept.utilities.player.DamagePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnHit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void sketchpad(EntityDamageEvent e) {
        if (e.getEntity() instanceof LivingEntity en) {
            if (ArmorUtilities.hasFullSet(en, Armor.SKETCHPAD)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void damagePlayer(EntityDamageByEntityEvent e) {
        if (!e.isCancelled()) {
            if (e.getEntity() instanceof Player p) {
                if (e.getDamager() instanceof LivingEntity en) {
                    e.setDamage(0);
                    if (DamagePlayer.melee(p, en)) e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void displayDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity en) {
            if (e.getDamager() instanceof Player p) {
                if (e.getEntity().getType()!= EntityType.ARMOR_STAND) {
                    en.setMaximumNoDamageTicks((int) (15/(1+(GetStats.getPlayer(p).getAttackSpeed()/100))));
                    DamageEntity.melee(p, en, 0, true);
                    e.setDamage(0);
                }
            }
        }
    }
}
