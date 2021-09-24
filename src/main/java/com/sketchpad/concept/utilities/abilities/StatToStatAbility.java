package com.sketchpad.concept.utilities.abilities;

import java.util.List;

public class StatToStatAbility extends Ability{
    public StatToStatAbility(List<String> lore, String name, int manaCost, int coolDown, Action action, Type type) {
        super(lore, name, manaCost, coolDown, action, type);
    }
}
