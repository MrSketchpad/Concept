package com.sketchpad.concept.items;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.abilities.*;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.player.TeleportPlayer;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public enum Sword implements ItemShell {
    ASPECT_OF_THE_VOID {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(120);
            stats.setStrength(100);

            Ability ability = new Ability(List.of(
                    "Teleport "+c.green("8")+" blocks ahead of",
                    "and gain "+c.speed(50),
                    "for "+c.green("3 seconds")+"."
            ), "Instant Transmission", 45,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);

            item = new ItemBase(Rarity.EPIC, "Aspect of the Void", Material.DIAMOND_SWORD,
                    ItemType.SWORD, ability, stats);
            item.setDungeon(true);
        }
    },
    ASPECT_OF_THE_END {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(100);
            stats.setStrength(100);

            Ability ability = new Ability(List.of(
                    "Teleport "+c.green("8")+" blocks ahead of",
                    "and gain "+c.speed(50),
                    "for "+c.green("3 seconds")+"."
            ), "Instant Transmission", 50,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY) {
                @Override
                public void onUse(Player p) {
                    TeleportPlayer.teleport(p, 8, true);
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                }
            };

            item = new ItemBase(Rarity.RARE, "Aspect of the End", Material.DIAMOND_SWORD,
                    ItemType.SWORD, ability, stats);
        }
    },
    ASPECT_OF_THE_AVERAGE_FORUMS_SUGGESTION {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(400,100,250,0,0,0,0,0,0, 0,
                    0,0,0,0,250,300,0, 0, 1000, 0);
            stats.setAttackSpeed(100);
            Ability ability = new Ability(List.of(
                    "Grants "+c.magicFind(15)+" for every",
                    c.green("1")+" health!"
            ), "Balanced", 0,0, Ability.Action.PASSIVE, Ability.Type.SINGLE_ABILITY);

            item = new ItemBase(Rarity.MYTHIC, "Aspect of the Average Forums Suggestion", Material.NETHERITE_SWORD,
                    ItemType.SWORD, stats, ability, (List.of(
                            "Yes I'm using this"+c.green(" to test"),
                            "colors, "+c.red("Why do you "+c.bold("ask")+c.green(c.italic("?")))
            )));
        }
    },
    RAGNAROK {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(245);
            stats.setStrength(145);
            stats.setFerocity(100);

            Ability ragnarok = new Ability(List.of(
                    "Damage dealt under "+c.green("500,000")+" is",
                    c.green("doubled")+"."
            ), "Ragnarok", 0,0, Ability.Action.PASSIVE, Ability.Type.SINGLE_ABILITY);

            Ability fullSet = new Ability(List.of(
                    "Upon landing a critical hit, create",
                    "a massive explosion, dealing "+c.green("25%"),
                    "of damage to all hit enemies."
            ), "Totality", 0,0, Ability.Action.PASSIVE, Ability.Type.SET_ABILITY);

            ItemBase item = new ItemBase(Rarity.LEGENDARY, "Ragnarok", Material.GOLDEN_SWORD, ItemType.SWORD,
                    List.of(ragnarok, fullSet
            ), stats);

            item.setSet("ragnarok");
            this.item = item;
        }
    },
    ASPECT_OF_THE_DRAGONS {
        @Override
        public void setObject() {
            SkyblockStats stats = SkyblockStats.getEmpty();
            stats.setDamage(225);
            stats.setStrength(100);

            Ability ability = new Ability(List.of(
                    "All Monsters in front of you",
                    "take "+c.green("700")+" damage. Hit",
                    "monsters take large knockback."
            ), "Dragon Rage", 100,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY);

            item = new ItemBase(Rarity.LEGENDARY, "Aspect of the Dragons", Material.DIAMOND_SWORD, ItemType.SWORD, ability, stats);
        }
    },
    ASPECT_OF_THE_TEST {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(100,100,100,100,100,100,100,100,100, 100,
                    100,100,100,100,100,100,100, 0, 1000, 2000000);
            stats.setFerocity(1000000);

            Ability ability = new Ability(List.of(
                    "Launches you into the air!"
            ), "One", 50,0, Ability.Action.SHIFT_RIGHT_CLICK, Ability.Type.SINGLE_ABILITY) {
                @Override
                public void onUse(Player p) {
                    p.setVelocity(p.getLocation().getDirection().multiply(5));
                }
            };

            Ability ability2 = new Ability(List.of(
                    "Strikes you with lightning!"
            ), "Two", 100,0, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY) {
                @Override
                public void onUse(Player p) {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                }
            };

            item = new ItemBase(Rarity.LEGENDARY, "Aspect of the Test", Material.DIAMOND_PICKAXE,
                    ItemType.SWORD, List.of(ability, ability2), stats);
        }
    },
    ASPECT_OF_THE_INTELLIGENT_TEST {
        @Override
        public void setObject() {
            SkyblockStats stats = new SkyblockStats(100,100,100,100,100,100,100,100,100, 100,
                    100,100,100,100,100,100,100, 0, 0, 0);

            Ability ability = new Ability(List.of(
                    "Grants "+c.intel(1000)+" for",
                    c.green("5 ")+"seconds!"
            ), "Absolute Testing", 0,10, Ability.Action.RIGHT_CLICK, Ability.Type.SINGLE_ABILITY) {
                @Override
                public void onUse(Player p) {
                    AtomicInteger time = new AtomicInteger();
                    GetStats.bonusIntelligence.putIfAbsent(p.getUniqueId(), 0.0);
                    double previousIntel = GetStats.bonusIntelligence.get(p.getUniqueId());
                    GetStats.bonusIntelligence.put(p.getUniqueId(), previousIntel+1000);
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(Concept.instance, () -> {
                        if (time.get()>=5) {
                            GetStats.bonusIntelligence.put(p.getUniqueId(), previousIntel);
                            return;
                        }
                        time.addAndGet(1);
                    },0,20L);
                }
            };

            item = new ItemBase(Rarity.EPIC, "Aspect of the Intelligent Test", Material.IRON_SWORD, ItemType.SWORD, ability, stats);
        }
    },
    ;
    public ItemBase item;
    public ItemBase getItem() {
        return item;
    }
    public static void setContained() {
        for (Sword s: Sword.values()) {
            s.setObject();
        }
    }
    public boolean equals(@Nullable ItemStack i) {
        if (i!=null) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("name"), PersistentDataType.STRING)) {
                    return Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING), item.getDisplayName());
                }
            }
        }
        return false;
    }
    public boolean equals(@Nullable ItemBase i) {
        if (i!=null) {
            return i.getDisplayName().equals(item.getDisplayName());
        }
        return false;
    }
}
