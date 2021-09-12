package com.sketchpad.concept.utilities.entities;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class UpdateEntity {
    public static void setNbt(LivingEntity en, double dmg, Player p) {
        SkyblockMob mob = SkyblockMob.fromLivingEntity(en);
        mob.setHealth(mob.getHealth()-dmg);
        mob.setHits(mob.getHits()+1);
        mob.modify(en, p);
    }
}
