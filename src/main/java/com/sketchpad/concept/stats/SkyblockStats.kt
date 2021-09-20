package com.sketchpad.concept.stats

class SkyblockStats(
    var health: Double,
    var intelligence: Double,
    var defense: Double,
    var ferocity: Double,
    var petLuck: Double,
    var magicFind: Double,
    var seaCreatureChance: Double,
    var trueDefense: Double,
    var critChance: Double,
    var critDamage: Double,
    var abiilityDamage: Double,
    var miningFortune: Double,
    var farmingFortune: Double,
    var foragingFortune: Double,
    var strength: Double,
    var damage: Double,
    var speed: Double,
    totalModifier: Double
) : Cloneable {
    var totalModifier: Double
    val isVisible: Boolean
        get() = health + intelligence + defense + damage + strength + critDamage + critChance + ferocity + speed > 0

    fun hasOffensive(): Boolean {
        return damage + strength + critDamage + critChance > 0
    }

    fun hasDefensive(): Boolean {
        return health + defense + intelligence + ferocity + trueDefense + magicFind > 0
    }

    fun add(skyblockStats: SkyblockStats): SkyblockStats {
        return SkyblockStats(skyblockStats.health + health,
            skyblockStats.intelligence + intelligence,
            skyblockStats.defense + defense,
            skyblockStats.ferocity + ferocity,
            skyblockStats.petLuck + petLuck,
            skyblockStats.magicFind + magicFind,
            skyblockStats.seaCreatureChance + seaCreatureChance,
            skyblockStats.trueDefense + trueDefense,
            skyblockStats.critChance + critChance,
            skyblockStats.critDamage + critDamage,
            skyblockStats.abiilityDamage + abiilityDamage,
            skyblockStats.miningFortune + miningFortune,
            skyblockStats.farmingFortune + farmingFortune,
            skyblockStats.foragingFortune + foragingFortune,
            skyblockStats.strength + strength,
            skyblockStats.damage + damage,
            skyblockStats.speed + speed,
            skyblockStats.totalModifier + totalModifier)
    }

    fun multiply(delta: Double): SkyblockStats {
        return SkyblockStats(health * delta,
            intelligence * delta,
            defense * delta,
            ferocity * delta,
            petLuck * delta,
            magicFind * delta,
            seaCreatureChance * delta,
            trueDefense * delta,
            critChance * delta,
            critDamage * delta,
            abiilityDamage * delta,
            miningFortune * delta,
            farmingFortune * delta,
            foragingFortune * delta,
            strength * delta,
            damage * delta,
            speed * delta,
            totalModifier * delta)
    }

    public override fun clone(): SkyblockStats {
        return SkyblockStats(health, intelligence, defense, ferocity, petLuck, magicFind,
            seaCreatureChance, trueDefense, critChance, critDamage, abiilityDamage,
            miningFortune, farmingFortune, foragingFortune, strength, damage,
            speed, totalModifier)
    }

    companion object {
        @JvmStatic
        val empty: SkyblockStats
            get() = SkyblockStats(
                .0, .0, .0, .0, .0, .0,
                .0, .0, .0, .0, .0, .0,
                .0, .0, .0, .0, .0, .0)
        @JvmStatic
        val player: SkyblockStats
            get() = SkyblockStats(100.0, 100.0, .0, .0, .0,
                .0, .0, .0, .0, .0, .0, .0,
                .0, .0, .0, .0, 100.0, .1)
    }

    init {
        if (speed > 400) speed = 400.0
        this.totalModifier = totalModifier
    }
}