package com.sketchpad.concept.utilities.enchantments;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.entities.CreatureType;
import com.sketchpad.concept.utilities.entities.SkyblockMob;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SkyblockEnchants {
    public @NotNull HashMap<Enchant, Integer> enchants = new HashMap<>();
    public SkyblockEnchants() {
        for (Enchant en:Enchant.values()) {
            for (Enchant e:en.getConflicting()) {
                if (enchants.containsKey(e)) {
                    if (enchants.get(e)>0) {
                        enchants.remove(e);
                    }
                }
            }
            enchants.put(en, 0);
        }
    }
    public SkyblockEnchants(@NotNull HashMap<Enchant, Integer> enchants) {
        this.enchants = enchants;
    }
    public void set(Enchant en, int value) {
        for (Enchant e:en.getConflicting()) {
            if (enchants.containsKey(e) && enchants.get(e)!=null) {
                if (enchants.get(e)>0) {
                    enchants.remove(e);
                }
            }
        }
        enchants.put(en, value);
    }
    public void setAll(@NotNull HashMap<Enchant, Integer> enchants) {
        this.enchants = enchants;
    }
    public @NotNull HashMap<Enchant, Integer> getAll() {
        return enchants;
    }
    public @NotNull HashMap<Enchant, Integer> getActive() {
        HashMap<Enchant, Integer> enchants = new HashMap<>();
        for (Enchant en:Enchant.values()) {
            if (this.enchants.containsKey(en) && this.enchants.get(en)!=null) {
                if (this.enchants.get(en)>0) {
                    enchants.put(en, this.enchants.get(en));
                }
            }
        }
        return enchants;
    }
    public double getDamageBonus(@NotNull SkyblockMob mob) {
        double modifier = 1;
        if (enchants.containsKey(Enchant.SHARPNESS) && enchants.get(Enchant.SHARPNESS)!=null) {
            double mod = enchants.get(Enchant.SHARPNESS);
            modifier += (mod/20);
        }
        if (mob.getCreatureType()== CreatureType.UNDEAD) {
            if (enchants.containsKey(Enchant.SMITE)) {
                modifier += (enchants.get(Enchant.SMITE))*0.08;
            }
        } else if (mob.getCreatureType()==CreatureType.ARTHROPOD) {
            if (enchants.containsKey(Enchant.BANE_OF_ARTHROPODS)) {
                modifier += (enchants.get(Enchant.BANE_OF_ARTHROPODS))*0.08;
            }
        }
        if (mob.getHits()<1) {
            if (enchants.containsKey(Enchant.FIRST_STRIKE) && enchants.get(Enchant.FIRST_STRIKE)!=null) {
                double mod = enchants.get(Enchant.FIRST_STRIKE);
                modifier += (mod/4);
            }
        }
        if (mob.getHits()<3) {
            if (enchants.containsKey(Enchant.TRIPLE_STRIKE) && enchants.get(Enchant.TRIPLE_STRIKE)!=null &&
            enchants.get(Enchant.TRIPLE_STRIKE)>0) {
                double mod = enchants.get(Enchant.TRIPLE_STRIKE);
                modifier += (mod/10);
            }
        }
        return modifier;
    }
    public double getHealingBonus(@NotNull Player p) {
        double modifier = 1;
        if (enchants.containsKey(Enchant.REJUVENATE) && enchants.get(Enchant.REJUVENATE)!=null &&
        enchants.get(Enchant.REJUVENATE)>0) {
            modifier -= (0.02*enchants.get(Enchant.REJUVENATE));
        }
        if (enchants.containsKey(Enchant.RESPITE) && enchants.get(Enchant.RESPITE)!=null &&
                enchants.get(Enchant.RESPITE)>0) {
            if (Concept.inCombat.get(p.getUniqueId())>=5) {
                modifier -= (0.05*enchants.get(Enchant.REJUVENATE));
            }
        }
        return modifier;
    }
    public boolean isEmpty() {
        for (Integer i:enchants.values()) {
            if (i!=null) {
                if (i>0) return false;
            }
        }
        return true;
    }
    public static @NotNull SkyblockEnchants getType(@NotNull ItemType type, int level) {
        HashMap<Enchant, Integer> enchants = new HashMap<>();
        for (Enchant en:Enchant.values()) {
            if (en.getType().getTypes().contains(type)) {
                enchants.put(en, level);
            }
        }
        return new SkyblockEnchants(enchants);
    }
    public static @NotNull SkyblockEnchants getAll(@NotNull ItemStack i) {
        HashMap<Enchant, Integer> enchants = new HashMap<>();
        for (Enchant en:Enchant.values()) {
            enchants.put(en, (Integer) NbtManager.getNbt(i, PersistentDataType.INTEGER, en.name().toLowerCase()));
        }
        return new SkyblockEnchants(enchants);
    }
    public static @NotNull SkyblockEnchants getEmpty(ItemType type) {
        HashMap<Enchant, Integer> enchants = new HashMap<>();
        for (Enchant en:Enchant.values()) {
            enchants.put(en, 0);
        }
        return new SkyblockEnchants(enchants);
    }
    public static @NotNull SkyblockEnchants getMax(ItemType type) {
        HashMap<Enchant, Integer> enchants = new HashMap<>();
        for (Enchant en:Enchant.values()) {
            for (Enchant e:en.getConflicting()) {
                if (enchants.containsKey(e)) {
                    if (enchants.get(e)>0) {
                        enchants.remove(e);
                    }
                }
            }
            if (en.getType().getTypes().contains(type)) {
                switch (en) {
                    case RESPITE -> en = Enchant.REJUVENATE;
                    case FROST_WALKER -> en = Enchant.DEPTH_STRIDER;
                    case PROSECUTE -> en = Enchant.EXECUTE;
                }
                enchants.put(en, en.getMaxValue());
            }
        }
        return new SkyblockEnchants(enchants);
    }
    public static @NotNull SkyblockEnchants getAll(Player p)  {
        HashMap<Enchant, Integer> enchants = getEmpty(ItemType.ALL).enchants;
        if (p.getInventory().getHelmet()!=null && p.getInventory().getHelmet().hasItemMeta()) {
            SkyblockItem item = SkyblockItem.fromItemStack(p.getInventory().getHelmet());
            for (Enchant en:Enchant.values()) {
                if (item.getEnchants().enchants.containsKey(en) && item.getEnchants().enchants.get(en)!=null) {
                    enchants.put(en, enchants.get(en)+item.getEnchants().enchants.get(en));
                }
            }
        }
        if (p.getInventory().getChestplate()!=null && p.getInventory().getChestplate().hasItemMeta()) {
            SkyblockItem item = SkyblockItem.fromItemStack(p.getInventory().getChestplate());
            for (Enchant en:Enchant.values()) {
                if (item.getEnchants().enchants.containsKey(en) && item.getEnchants().enchants.get(en)!=null) {
                    enchants.put(en, enchants.get(en)+item.getEnchants().enchants.get(en));
                }
            }
        }
        if (p.getInventory().getLeggings()!=null && p.getInventory().getLeggings().hasItemMeta()) {
            SkyblockItem item = SkyblockItem.fromItemStack(p.getInventory().getLeggings());
            for (Enchant en:Enchant.values()) {
                if (item.getEnchants().enchants.containsKey(en) && item.getEnchants().enchants.get(en)!=null) {
                    enchants.put(en, enchants.get(en)+item.getEnchants().enchants.get(en));
                }
            }
        }
        if (p.getInventory().getBoots()!=null && p.getInventory().getBoots().hasItemMeta()) {
            SkyblockItem item = SkyblockItem.fromItemStack(p.getInventory().getBoots());
            for (Enchant en:Enchant.values()) {
                if (item.getEnchants().enchants.containsKey(en) && item.getEnchants().enchants.get(en)!=null) {
                    enchants.put(en, enchants.get(en)+item.getEnchants().enchants.get(en));
                }
            }
        }
        return new SkyblockEnchants(enchants);
    }

}
