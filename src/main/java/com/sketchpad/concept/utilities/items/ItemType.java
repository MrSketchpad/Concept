package com.sketchpad.concept.utilities.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemType {
    MAIN_HAND(false, false),
    OFF_HAND(true, false),
    SWORD(true, false),
    SHIELD(true, false),
    BOW(true, false),
    SHORTBOW(true, false),
    HELMET(true, false),
    CHESTPLATE(true, false),
    LEGGINGS(true, false),
    BOOTS(true, false),
    ARMOR(true, false),
    TOOL(true, false),
    ACCESSORY(true, false),
    UPGRADEABLE(false, true),
    MATERIAL(false, false),
    REFORGE_STONE(false, true),
    INVENTORY(false, false),
    ALL(false, false),
    ERROR(false, false),
    ;
    boolean reforgeable;
    boolean upgrade;
    ItemType(boolean reforgeable, boolean upgrade) {
        this.reforgeable = reforgeable;
        this.upgrade = upgrade;
    }
    public boolean isReforgeable() {
        return reforgeable;
    }
    public boolean isUpgrade() {
        return upgrade;
    }
    public List<ItemType> getTypes() {
        List<ItemType> types = new ArrayList<>();
        types.add(this);
        switch (this) {
            case MAIN_HAND -> {
                types.add(SWORD);
                types.add(TOOL);
                types.add(ACCESSORY);
                types.add(BOW);
                types.add(SHORTBOW);
            }
            case ARMOR -> {
                types.add(HELMET);
                types.add(CHESTPLATE);
                types.add(LEGGINGS);
                types.add(BOOTS);
            }
            case OFF_HAND -> types.add(SHIELD);
            case ALL -> types.addAll(Arrays.asList(ItemType.values()));
        }
        return types;
    }
    public static ItemType fromString(String s) {
        for (ItemType t:ItemType.values()) {
            if (t.name().toLowerCase().replace("_", "").equals(s)) {
                return t;
            }
        }
        return ERROR;
    }
}
