package com.sketchpad.concept.utilities.items;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE, "COMMON", 1),
    UNCOMMON(ChatColor.GREEN, "UNCOMMON", 2),
    RARE(ChatColor.BLUE, "RARE", 3),
    EPIC(ChatColor.DARK_PURPLE, "EPIC", 4),
    LEGENDARY(ChatColor.GOLD, "LEGENDARY", 5),
    MYTHIC(ChatColor.LIGHT_PURPLE, "MYTHIC", 6),
    DIVINE(ChatColor.AQUA, "DIVINE", 7),
    SPECIAL(ChatColor.RED, "SPECIAL", 6),
    VERY_SPECIAL(ChatColor.RED, "VERY SPECIAL", 6),
    ERROR(ChatColor.RED, "ERROR", 0),
    ;
    private final ChatColor color;
    private final String displayName;
    private final int value;
    Rarity(ChatColor color, String displayName, int value) {
        this.color = color;
        this.displayName = displayName;
        this.value = value;
    }
    public ChatColor getColor() {
        return color;
    }
    public int getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Rarity fromString(String s) {
        for (Rarity r:Rarity.values()) {
            if (r.name().toLowerCase().replace("_", "").equals(s)) {
                return r;
            }
        }
        return ERROR;
    }
    public static Rarity getAbove(Rarity s) {
        for (Rarity r:Rarity.values()) {
            if (r.getValue()-1==s.getValue()) return r;
        }
        return COMMON;
    }
}
