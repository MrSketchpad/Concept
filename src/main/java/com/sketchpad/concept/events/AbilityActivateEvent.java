package com.sketchpad.concept.events;

import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class AbilityActivateEvent extends Event {
    private static final @NotNull HandlerList HANDLERS = new HandlerList();
    private final double mana;
    private double manaConsumed;
    private final @NotNull SkyblockItem item;
    private final @NotNull Ability ab;
    private final @NotNull Player p;
    private boolean cancelled = false;

    public AbilityActivateEvent(double mana, double manaConsumed, @NotNull SkyblockItem item,
                                @NotNull Ability ab, @NotNull Player p) {
        this.mana = mana;
        this.manaConsumed = manaConsumed;
        this.item = item;
        this.ab = ab;
        this.p = p;
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
    public double getMana() {
        return mana;
    }
    public double getManaConsumed() {
        return manaConsumed;
    }
    public @NotNull SkyblockItem getItem() {
        return item;
    }
    public @NotNull Ability getAbility() {
        return ab;
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
    public void setManaConsumed(double manaConsumed) {
        this.manaConsumed = manaConsumed;
    }
}
