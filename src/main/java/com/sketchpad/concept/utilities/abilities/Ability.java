package com.sketchpad.concept.utilities.abilities;

import org.bukkit.ChatColor;

import java.util.List;

public class Ability {
    public enum Action {
        RIGHT_CLICK(ChatColor.YELLOW+""+ChatColor.BOLD+"RIGHT CLICK"),
        LEFT_CLICK(ChatColor.YELLOW+""+ChatColor.BOLD+"LEFT CLICK"),
        SHIFT_RIGHT_CLICK(ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK+RIGHT CLICK"),
        SHIFT_LEFT_CLICK(ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK+LEFT CLICK"),
        SNEAK(ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK"),
        JUMP(ChatColor.YELLOW+""+ChatColor.BOLD+"JUMP"),
        PASSIVE(""),
        ;
        private final String text;
        Action(String text) {
            this.text = text;
        }
        public String getText() {
            return text;
        }
    }
    public enum Type {
        FULL_SET(ChatColor.GOLD+"Full Set Bonus: "),
        SINGLE_ABILITY(ChatColor.GOLD+"Item Ability: "),
        SET_ABILITY(ChatColor.GOLD+"Set Ability: ")
        ;
        private final String text;
        Type(String text) {
            this.text = text;
        }
        public String getText() {
            return text;
        }
    }
    private List<String> lore;
    private String name;
    private int manaCost;
    private int coolDown;
    private Action action;
    private Type type;
    private long timeUsed = 0;

    public Ability(List<String> lore, String name, int manaCost, int coolDown, Action action, Type type) {
        this.lore = lore;
        this.name = name;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
        this.action = action;
        this.type = type;
    }
    public boolean isEmpty() {
        return (name.equals(""));
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public List<String> getLore() {
        return lore;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
    public void setAction(Action type) {
        this.action = type;
    }
    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
    public Action getAction() {
        return action;
    }
    public int getCoolDown() {
        return coolDown;
    }
    public int getManaCost() {
        return manaCost;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }
    public long getTimeUsed() {
        return timeUsed;
    }
    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }
}
