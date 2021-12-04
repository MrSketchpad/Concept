package com.sketchpad.concept.handlers;

import com.sketchpad.concept.utilities.entities.CreatureType;
import com.sketchpad.concept.utilities.entities.SkyblockMob;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Objects;

public class OnCreatureSpawn implements Listener {
    @EventHandler
    public void toSb(CreatureSpawnEvent e) {
        if (e.getEntity().getPersistentDataContainer().isEmpty()
        && e.getEntity().getType()!= EntityType.ARMOR_STAND) {
            LivingEntity en = e.getEntity();
            SkyblockMob mob = new SkyblockMob(en.getHealth()*100, en.getHealth()*100,
                    Objects.requireNonNull(en.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue()*10,
                    (int) en.getHealth(), en.getName(), en.getType(), CreatureType.BARN, 0);
            mob.modify(en, null);
        }
    }
}
