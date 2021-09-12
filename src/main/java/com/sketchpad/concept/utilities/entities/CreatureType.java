package com.sketchpad.concept.utilities.entities;

public enum CreatureType {
    BARN,
    UNDEAD,
    ARTHROPOD,
    ;
    public static CreatureType fromString(String s) {
        for (CreatureType type:CreatureType.values()) {
            if (s.equals(type.name())) return type;
        }
        return null;
    }
}
