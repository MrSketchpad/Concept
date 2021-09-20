package com.sketchpad.concept.events;

import com.sketchpad.concept.stats.SkyblockStats;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NaturalRegenEvent extends Event {
    private static final @NotNull HandlerList HANDLERS = new HandlerList();
    private @NotNull final SkyblockStats stats;
    private final @NotNull Player p;
    private double healthIncreased;
    private double manaIncreased;
    private boolean cancelled = false;

    public NaturalRegenEvent(@NotNull SkyblockStats stats, @NotNull Player p, double healthIncreased, double manaIncreased) {
        this.stats = stats;
        this.p = p;
        this.healthIncreased = healthIncreased;
        this.manaIncreased = manaIncreased;
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
    public SkyblockStats getStats() {
        return stats;
    }
    public void setManaIncreased(double i) {
        manaIncreased = i;
    }
    public void setHealthIncreased(double i) {
        healthIncreased = i;
    }
    public double getHealthIncreased() {
        return healthIncreased;
    }
    public double getManaIncreased() {
        return manaIncreased;
    }
}
