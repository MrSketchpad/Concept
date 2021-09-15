package com.sketchpad.concept.utilities.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemType {
    MAIN_HAND(true),
    SWORD(true),
    BOW(true),
    HELMET(true),
    CHESTPLATE(true),
    LEGGINGS(true),
    BOOTS(true),
    ARMOR(true),
    TOOL(true),
    ACCESSORY(true),
    MATERIAL(false),
    REFORGE_STONE(false),
    INVENTORY(false),
    ALL(false),
    ERROR(false),
    ;
    boolean reforgeable;
    ItemType(boolean reforgeable) {
        this.reforgeable = reforgeable;
    }
    public boolean isReforgeable() {
        return reforgeable;
    }
    public List<ItemType> getTypes() {
        List<ItemType> types = new ArrayList<>();
        types.add(this);
        switch (this) {
            case MAIN_HAND -> {
                types.add(SWORD);
                types.add(TOOL);
                types.add(ACCESSORY);
            }
            case ARMOR -> {
                types.add(HELMET);
                types.add(CHESTPLATE);
                types.add(LEGGINGS);
                types.add(BOOTS);
            }
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
