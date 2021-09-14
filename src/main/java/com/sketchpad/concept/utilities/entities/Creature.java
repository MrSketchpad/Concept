package com.sketchpad.concept.utilities.entities;

import com.sketchpad.concept.utilities.items.ItemShell;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Creature {
    MISTER_SHEEP(new SkyblockMob(1000000, 1000000, 100, 100, "Mister Shep", EntityType.SHEEP, CreatureType.BARN, 0)),
    SIR_ZOMBIE(new SkyblockMob(1000000, 1000000, 100, 100, "Sir Zomb", EntityType.ZOMBIE, CreatureType.UNDEAD, 0)),
    MASTER_ZOMBIE(new SkyblockMob(1000000, 1000000, 1000, 100, "Master Zomb", EntityType.ZOMBIE, CreatureType.UNDEAD, 100)),
    ;
    SkyblockMob mob;
    Creature(SkyblockMob mob) {
        this.mob = mob;
    }
    public @Nullable SkyblockMob getMob() {
        return mob;
    }
    public static @Nullable
    Creature getFromString(@NotNull String s) {
        for (Creature c:Creature.values()) {
            if (c.mob.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }
}
