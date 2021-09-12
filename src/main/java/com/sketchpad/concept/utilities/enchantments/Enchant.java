package com.sketchpad.concept.utilities.enchantments;

import com.sketchpad.concept.utilities.items.ItemType;

import java.util.ArrayList;
import java.util.List;

public enum Enchant {
    AQUA_AFFINITY("Aqua Affinity", 1, ItemType.HELMET),
    BIG_BRAIN("Big Brain", 5, ItemType.HELMET),
    BLAST_PROTECTION("Blast Protection", 7, ItemType.ARMOR),
    BANE_OF_ARTHROPODS("Bane of Arthropods", 7, ItemType.SWORD),
    CLEAVE("Cleave", 6, ItemType.SWORD),
    CRITICAL("Critical", 7, ItemType.SWORD),
    CUBISM("Cubism", 6, ItemType.SWORD),
    COUNTER_STRIKE("Counter-Strike", 5, ItemType.CHESTPLATE),
    DRAGON_HUNTER("Dragon Hunter", 5, ItemType.SWORD),
    DEPTH_STRIDER("Depth Strider", 3, ItemType.BOOTS),
    ENDER_SLAYER("Ender Slayer", 7, ItemType.SWORD),
    EXECUTE("Execute", 6, ItemType.SWORD),
    EXPERIENCE("Experience", 4, ItemType.SWORD),
    FIRE_ASPECT("Fire Aspect", 2, ItemType.SWORD),
    FIRST_STRIKE("First Strike", 5, ItemType.SWORD),
    FEATHER_FALLING("Feather Falling", 5, ItemType.BOOTS),
    FIRE_PROTECTION("Fire Protection", 7, ItemType.ARMOR),
    FROST_WALKER("Frost Walker", 2, ItemType.BOOTS),
    GIANT_KILLER("Giant Killer", 7, ItemType.SWORD),
    GROWTH("Growth", 7, ItemType.ARMOR),
    IMPALING("Impaling", 3, ItemType.SWORD),
    KNOCKBACK("Knockback", 2, ItemType.SWORD),
    LETHALITY("Lethality", 6, ItemType.SWORD),
    LIFE_STEAL("Life Steal", 5, ItemType.SWORD),
    LOOTING("Looting", 5, ItemType.SWORD),
    LUCK("Luck", 7, ItemType.SWORD),
    PROSECUTE("Prosecute", 6, ItemType.SWORD),
    PROJECTILE_PROTECTION("Projectile Protection", 7, ItemType.ARMOR),
    PROTECTION("Protection", 7, ItemType.ARMOR),
    REJUVENATE("Rejuvenate", 5, ItemType.ARMOR),
    RESPIRATION("Respiration", 3, ItemType.HELMET),
    RESPITE("Respite", 5, ItemType.ARMOR),
    SCAVENGER("Scavenger", 5, ItemType.SWORD),
    SHARPNESS("Sharpness", 7, ItemType.SWORD),
    SMITE("Smite", 7, ItemType.SWORD),
    SYPHON("Syphon", 5, ItemType.SWORD),
    TELEKINESIS("Telekinesis", 1, ItemType.SWORD),
    THUNDERBLOT("Thunderbolt", 6, ItemType.SWORD),
    THORNS("Thorns", 3, ItemType.ARMOR),
    TRUE_PROTECTION("True Protection", 1, ItemType.CHESTPLATE),
    TITAN_KILLER("Titan Killer", 7, ItemType.SWORD),
    TRIPLE_STRIKE("Triple Strike", 5, ItemType.SWORD),
    VAMPIRISM("Vampirism", 6, ItemType.SWORD),
    VENOMOUS("Venomous", 6, ItemType.SWORD),
    VICIOUS("Vicious", 5, ItemType.SWORD),
    SMARTY_PANTS("Smarty Pants", 5, ItemType.LEGGINGS),
    SUGAR_RUSH("Sugar Rush", 3, ItemType.BOOTS),
    FILL("", 0, ItemType.ERROR),
    ;
    String name;
    int max;
    ItemType type;
    Enchant(String name, int max, ItemType type) {
        this.name = name;
        this.max = max;
        this.type =type;
    }
    public String getName() {
        return name;
    }

    public int getMaxValue() {
        return max;
    }
    public static Enchant fromString(String s) {
        for (Enchant en : Enchant.values()) {
            if (en.name().equals(s)) {
                return en;
            }
        }
        return null;
    }
    public ItemType getType() {
        return type;
    }
    public List<Enchant> getConflicting() {
        List<Enchant> enchants = new ArrayList<>();
        switch (this) {
            case SHARPNESS -> {
                enchants.add(BANE_OF_ARTHROPODS);
                enchants.add(SMITE);
            }
            case SMITE -> {
                enchants.add(BANE_OF_ARTHROPODS);
                enchants.add(SHARPNESS);
            }
            case BANE_OF_ARTHROPODS -> {
                enchants.add(SHARPNESS);
                enchants.add(SMITE);
            }
            case PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case PROJECTILE_PROTECTION -> {
                enchants.add(PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case FIRE_PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case BLAST_PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(PROTECTION);
            }
            case FIRST_STRIKE -> enchants.add(TRIPLE_STRIKE);
            case TRIPLE_STRIKE -> enchants.add(FIRST_STRIKE);
            case GIANT_KILLER -> enchants.add(TITAN_KILLER);
            case TITAN_KILLER -> enchants.add(GIANT_KILLER);
            case LIFE_STEAL -> enchants.add(SYPHON);
            case SYPHON -> enchants.add(LIFE_STEAL);
            case PROSECUTE -> enchants.add(EXECUTE);
            case EXECUTE -> enchants.add(PROSECUTE);
            case RESPITE -> enchants.add(REJUVENATE);
            case REJUVENATE -> enchants.add(RESPITE);
        }
        return enchants;
    }
}
