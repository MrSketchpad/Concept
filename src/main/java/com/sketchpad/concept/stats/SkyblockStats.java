package com.sketchpad.concept.stats;

public class SkyblockStats {
    private double health = 0;
    private double intelligence = 0;
    private double defense = 0;
    private double damage = 0;
    private double strength = 0;
    private double trueDefense = 0;
    private double critChance = 0;
    private double critDamage = 0;
    private double seaCreatureChance = 0;
    private double magicFind = 0;
    private double petLuck = 0;
    private double abiilityDamage = 0;
    private double ferocity = 0;
    private double miningFortune = 0;
    private double farmingFortune = 0;
    private double foragingFortune = 0;
    private double speed = 0;
    private double totalModifier = 0;
    private double miningSpeed = 0;
    private double attackSpeed = 0;
    public SkyblockStats(double health, double intelligence, double defense, double ferocity, double petLuck, double magicFind, double seaCreatureChance, double
            trueDefense, double critChance, double critDamage, double abiilityDamage, double miningFortune, double farmingFortune, double foragingFortune,
                         double strength, double damage, double speed, double totalModifier, double miningSpeed, double attackSpeed) {
        this.health = health;
        this.intelligence = intelligence;
        this.defense = defense;
        this.ferocity = ferocity;
        this.petLuck = petLuck;
        this.magicFind = magicFind;
        this.seaCreatureChance = seaCreatureChance;
        this.trueDefense = trueDefense;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.abiilityDamage = abiilityDamage;
        this.miningFortune = miningFortune;
        this.farmingFortune = farmingFortune;
        this.foragingFortune = foragingFortune;
        this.strength = strength;
        this.damage = damage;
        this.speed = speed;
        if (speed>400) this.speed = 400;
        this.totalModifier = totalModifier;
        this.miningSpeed = miningSpeed;
        this.attackSpeed = attackSpeed;
    }
    public double getDefense() {
        return defense;
    }
    public double getHealth() {
        return health;
    }
    public double getIntelligence() {
        return intelligence;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getAbiilityDamage() {
        return abiilityDamage;
    }
    public void setAbiilityDamage(double abiilityDamage) {
        this.abiilityDamage = abiilityDamage;
    }
    public double getCritChance() {
        return critChance;
    }
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }
    public double getCritDamage() {
        return critDamage;
    }
    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }
    public double getFarmingFortune() {
        return farmingFortune;
    }
    public void setFarmingFortune(double farmingFortune) {
        this.farmingFortune = farmingFortune;
    }
    public double getFerocity() {
        return ferocity;
    }
    public void setFerocity(double ferocity) {
        this.ferocity = ferocity;
    }
    public double getForagingFortune() {
        return foragingFortune;
    }
    public void setForagingFortune(double foragingFortune) {
        this.foragingFortune = foragingFortune;
    }
    public double getMagicFind() {
        return magicFind;
    }
    public void setMagicFind(double magicFind) {
        this.magicFind = magicFind;
    }
    public double getMiningFortune() {
        return miningFortune;
    }
    public void setMiningFortune(double miningFortune) {
        this.miningFortune = miningFortune;
    }
    public double getPetLuck() {
        return petLuck;
    }
    public void setPetLuck(double petLuck) {
        this.petLuck = petLuck;
    }
    public double getSeaCreatureChance() {
        return seaCreatureChance;
    }
    public void setSeaCreatureChance(double seaCreatureChance) {
        this.seaCreatureChance = seaCreatureChance;
    }
    public double getTrueDefense() {
        return trueDefense;
    }
    public void setTrueDefense(double trueDefense) {
        this.trueDefense = trueDefense;
    }
    public boolean isVisible() {
        return (health+intelligence+defense+damage+strength+critDamage+critChance+ferocity+speed>0);
    }
    public boolean hasOffensive() {
        return (damage+strength+critDamage+critChance>0);
    }
    public boolean hasDefensive() {
        return (health+defense+intelligence+ferocity+trueDefense+magicFind>0);
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getTotalModifier() {
        return totalModifier;
    }
    public void setTotalModifier(double totalModifier) {
        this.totalModifier = totalModifier;
    }
    public double getMiningSpeed() {
        return miningSpeed;
    }
    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }
    public double getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public static SkyblockStats getPlayer() {
        return new SkyblockStats(100,100,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,100,1, 0, 0);
    }
    public SkyblockStats add(SkyblockStats skyblockStats) {
        return new SkyblockStats(skyblockStats.getHealth()+health, skyblockStats.getIntelligence()+intelligence, skyblockStats.getDefense()+defense, skyblockStats.getFerocity()+ferocity,
                skyblockStats.getPetLuck()+petLuck, skyblockStats.getMagicFind()+magicFind, skyblockStats.getSeaCreatureChance()+seaCreatureChance,
                skyblockStats.getTrueDefense()+trueDefense, skyblockStats.getCritChance()+critChance, skyblockStats.getCritDamage()+critDamage,
                skyblockStats.getAbiilityDamage()+abiilityDamage, skyblockStats.getMiningFortune()+miningFortune, skyblockStats.getFarmingFortune()+farmingFortune,
                skyblockStats.getForagingFortune()+foragingFortune, skyblockStats.getStrength()+strength, skyblockStats.getDamage()+damage, skyblockStats.getSpeed()+speed,
                skyblockStats.getTotalModifier()+totalModifier, skyblockStats.miningSpeed+miningSpeed, skyblockStats.attackSpeed+attackSpeed);
    }
    public SkyblockStats multiply(double delta) {
        return new SkyblockStats(health*delta, intelligence*delta, defense*delta, ferocity*delta, petLuck*delta, magicFind*delta,
                seaCreatureChance*delta, trueDefense*delta, critChance*delta, critDamage*delta, abiilityDamage*delta,
                miningFortune*delta, farmingFortune*delta, foragingFortune*delta, strength*delta, damage*delta,
                speed*delta, totalModifier*delta, miningSpeed*delta, attackSpeed*delta);
    }
    public SkyblockStats clone() {
        return new SkyblockStats(health, intelligence, defense, ferocity, petLuck, magicFind,
                seaCreatureChance, trueDefense, critChance, critDamage, abiilityDamage,
                miningFortune, farmingFortune, foragingFortune, strength, damage,
                speed, totalModifier, miningSpeed, attackSpeed);
    }
    public static SkyblockStats getEmpty() {
        return new SkyblockStats(0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0, 0);
    }
}
