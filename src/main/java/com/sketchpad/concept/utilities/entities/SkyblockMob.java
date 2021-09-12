package com.sketchpad.concept.utilities.entities;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

@SuppressWarnings("ConstantConditions")
public class SkyblockMob {
    private double health;
    private double maxHealth;
    private double damage;
    private double defense;
    private int level;
    private String name;
    private EntityType type;
    private int hits = 0;
    private Location location;
    private boolean exists = false;
    private CreatureType creatureType;
    public SkyblockMob(double health, double maxHealth, double damage, int level, String name,
                       EntityType type, CreatureType creatureType, double defense) {
        this.health = health;
        this.maxHealth= maxHealth;
        this.damage = damage;
        this.level = level;
        this.name = name;
        this.type = type;
        this.creatureType = creatureType;
        this.defense = defense;
    }
    public LivingEntity spawn(Location loc, Player p) {
        LivingEntity en = (LivingEntity) loc.getWorld().spawnEntity(loc, type);
        en.getPersistentDataContainer().set(Concept.getKey("name"), PersistentDataType.STRING, name);
        en.getPersistentDataContainer().set(Concept.getKey("defense"), PersistentDataType.DOUBLE, defense);
        en.getPersistentDataContainer().set(Concept.getKey("health"), PersistentDataType.DOUBLE, health);
        en.getPersistentDataContainer().set(Concept.getKey("maxHealth"), PersistentDataType.DOUBLE, maxHealth);
        en.getPersistentDataContainer().set(Concept.getKey("level"), PersistentDataType.INTEGER, level);
        en.getPersistentDataContainer().set(Concept.getKey("damage"), PersistentDataType.DOUBLE, damage);
        en.getPersistentDataContainer().set(Concept.getKey("firstStrike"), PersistentDataType.BYTE, (byte)1);
        en.getPersistentDataContainer().set(Concept.getKey("type"), PersistentDataType.STRING, creatureType.name());
        en.getPersistentDataContainer().set(Concept.getKey("hits"), PersistentDataType.INTEGER, hits);
        this.location = loc;
        this.exists = true;
        UpdateDisplayHealth.update(en, p);
        return en;
    }
    public void modify(LivingEntity en, Player p) {
        en.getPersistentDataContainer().set(Concept.getKey("name"), PersistentDataType.STRING, name);
        en.getPersistentDataContainer().set(Concept.getKey("defense"), PersistentDataType.DOUBLE, defense);
        en.getPersistentDataContainer().set(Concept.getKey("health"), PersistentDataType.DOUBLE, health);
        en.getPersistentDataContainer().set(Concept.getKey("maxHealth"), PersistentDataType.DOUBLE, maxHealth);
        en.getPersistentDataContainer().set(Concept.getKey("level"), PersistentDataType.INTEGER, level);
        en.getPersistentDataContainer().set(Concept.getKey("damage"), PersistentDataType.DOUBLE, damage);
        en.getPersistentDataContainer().set(Concept.getKey("type"), PersistentDataType.STRING, creatureType.name());
        en.getPersistentDataContainer().set(Concept.getKey("hits"), PersistentDataType.INTEGER, hits);
        if (hits<1) en.getPersistentDataContainer().set(Concept.getKey("firstStrike"), PersistentDataType.BYTE, (byte)1);
        else en.getPersistentDataContainer().set(Concept.getKey("firstStrike"), PersistentDataType.BYTE, (byte)0);
        this.location = en.getLocation();
        this.exists = true;
        UpdateDisplayHealth.update(en, p);
    }
    public static SkyblockMob fromLivingEntity(LivingEntity e) {
        double health = e.getPersistentDataContainer().get(Concept.getKey("health"), PersistentDataType.DOUBLE);
        double maxHealth = e.getPersistentDataContainer().get(Concept.getKey("maxHealth"), PersistentDataType.DOUBLE);
        double damage = e.getPersistentDataContainer().get(Concept.getKey("damage"), PersistentDataType.DOUBLE);
        double defense = e.getPersistentDataContainer().get(Concept.getKey("defense"), PersistentDataType.DOUBLE);
        int level = e.getPersistentDataContainer().get(Concept.getKey("level"), PersistentDataType.INTEGER);
        int hits = e.getPersistentDataContainer().get(Concept.getKey("hits"), PersistentDataType.INTEGER);
        String name = e.getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING);
        CreatureType type = CreatureType.fromString(e.getPersistentDataContainer().get(Concept.getKey("type"), PersistentDataType.STRING));
        SkyblockMob mob = new SkyblockMob(health, maxHealth, damage, level, name, e.getType(), type, defense);
        mob.setHits(hits);
        mob.setLocation(e.getLocation());
        mob.setExists(true);
        return mob;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }
    public String getName() {
        return name;
    }
    public double getDamage() {
        return damage;
    }
    public double getHealth() {
        return health;
    }
    public double getMaxHealth() {
        return maxHealth;
    }
    public int getLevel() {
        return level;
    }
    public void setType(EntityType type) {
        this.type = type;
    }
    public void setCreatureType(CreatureType creatureType) {
        this.creatureType = creatureType;
    }
    public CreatureType getCreatureType() {
        return creatureType;
    }
    public EntityType getType() {
        return type;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public int getHits() {
        return hits;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }
    public boolean isExists() {
        return exists;
    }
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public double getDefense() {
        return defense;
    }
}
