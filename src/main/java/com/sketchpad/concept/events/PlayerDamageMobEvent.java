package com.sketchpad.concept.events;

import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.entities.SkyblockMob;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerDamageMobEvent extends Event {
    private static final @NotNull
    HandlerList HANDLERS = new HandlerList();
    private @NotNull final SkyblockMob mob;
    private final @NotNull Player p;
    private double damage;
    private int hits;
    private boolean cancelled = false;
    private boolean crit;
    private final SkyblockItem weapon;
    private boolean setAbility = false;
    private final LivingEntity livingEntity;

    public PlayerDamageMobEvent(@NotNull SkyblockMob mob, @NotNull Player p, double damage, int hits, boolean critical, SkyblockItem weapon, LivingEntity en) {
        this.mob = mob;
        this.p = p;
        this.damage = damage;
        this.hits = hits;
        this.crit = critical;
        this.weapon = weapon;
        livingEntity = en;
    }
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    public @NotNull Player getPlayer() {
        return p;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    @NotNull
    public SkyblockMob getMob() {
        return mob;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public boolean isCrit() {
        return crit;
    }
    public void setCrit(boolean crit) {
        this.crit = crit;
    }
    public SkyblockItem getWeapon() {
        return weapon;
    }
    public void setSetAbility(boolean setAbility) {
        this.setAbility = setAbility;
    }
    public boolean isSetAbility() {
        return setAbility;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }
}
