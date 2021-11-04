package com.sketchpad.concept.handlers;

import com.sketchpad.concept.events.PlayerDamageMobEvent;
import com.sketchpad.concept.items.Sword;
import com.sketchpad.concept.utilities.entities.DamageEntity;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class OnSkyblockHit implements Listener {
    @EventHandler
    public void builtInDamage(PlayerDamageMobEvent event) {
        event.getWeapon().getBase().onHit(event);
    }
    @EventHandler
    public void ragnarok(PlayerDamageMobEvent e) {
        if (e.isSetAbility()) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInOffHand().hasItemMeta()) {
                SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                SkyblockItem f = SkyblockItem.fromItemStack(p.getInventory().getItemInOffHand());
                if (i.isFullSet(f.getBase()) && i.getBase()==Sword.RAGNAROK.item && e.isCrit()) {
                    p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1);
                    List<Entity> entities = p.getNearbyEntities(5,2,5);
                    if (!entities.isEmpty()) {
                        for (Entity en:entities) {
                            if (en instanceof LivingEntity && en.getType() != EntityType.ARMOR_STAND) {
                                DamageEntity.melee(e.getPlayer(), e.getLivingEntity(), e.getDamage()/4, false);
                            }
                        }
                    }
                }
            }
        }
    }
}
