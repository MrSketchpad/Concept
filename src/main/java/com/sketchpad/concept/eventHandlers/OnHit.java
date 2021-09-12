package com.sketchpad.concept.eventHandlers;

import com.sketchpad.concept.utilities.entities.DamageEntity;
import com.sketchpad.concept.utilities.entities.SkyblockMob;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnHit implements Listener {
    @EventHandler
    public void damagePlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (e.getDamager() instanceof LivingEntity en) {
                SkyblockMob mob = SkyblockMob.fromLivingEntity(en);

            }
        }
    }
    @EventHandler
    public void displayDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity en) {
            if (e.getDamager() instanceof Player p) {
                if (e.getEntity().getType()!= EntityType.ARMOR_STAND) {
                    DamageEntity.melee(p, en);
                    e.setDamage(0);
                }
            }
        }
    }
}
