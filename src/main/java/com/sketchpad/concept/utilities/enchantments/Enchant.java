package com.sketchpad.concept.utilities.enchantments;

import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.items.ItemShell;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Enchant implements ItemShell {
    AQUA_AFFINITY("Aqua Affinity", 1, ItemType.HELMET, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases your underwater mining");
            lore.add(ChatColor.GRAY+"rate.");
            this.desc = lore;
        }
    },
    BIG_BRAIN("Big Brain", 5, ItemType.HELMET, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.AQUA+"+[5] ✎ Intelligence"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    BLAST_PROTECTION("Blast Protection", 7, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+[30] ❈ Defense");
            lore.add(ChatColor.GRAY+"against explosions.");
            this.desc = lore;
        }
    },
    BOND("Bond", 7, ItemType.SET, false) {
        @Override
        public void setObject() {
            this.desc = Arrays.asList(
                    ChatColor.GRAY+"Increases the stats of this",
                    ChatColor.GRAY+"item by "+ChatColor.GREEN+"[2]%"+ChatColor.GRAY+" if you have",
                    ChatColor.GRAY+"this item's set equipped."
            );
        }
    },
    BANE_OF_ARTHROPODS("Bane of Arthropods", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt to");
            lore.add(ChatColor.GRAY+"Spiders, Cave Spiders,");
            lore.add(ChatColor.GRAY+"and Silverfish by "+ChatColor.GREEN+"[8]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    CLEAVE("Cleave", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Deals "+ChatColor.GREEN+"[3]%"+ChatColor.GRAY+" of your damage");
            lore.add(ChatColor.GRAY+"dealt to other monsters within");
            lore.add(ChatColor.GREEN+"4.5"+ChatColor.GRAY+" blocks of the target.");
            this.desc = lore;
        }
    },
    CRITICAL("Critical", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases your "+ChatColor.BLUE+"☠ Crit Damage"+ChatColor.GRAY+" by");
            lore.add(ChatColor.GREEN+"[10]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    CUBISM("Cubism", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases the damage dealt to");
            lore.add(ChatColor.GRAY+"Magma Cubes, Creepers,");
            lore.add(ChatColor.GRAY+"and Slimes by "+ChatColor.GREEN+"[10]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    COUNTER_STRIKE("Counter-Strike", 5, ItemType.CHESTPLATE, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Gain "+ChatColor.GREEN+"+[2] ❈ Defense"+ChatColor.GRAY+" for "+ChatColor.GREEN+"7s");
            lore.add(ChatColor.GRAY+"on the first hit from an");
            lore.add(ChatColor.GRAY+"enemy.");
            this.desc = lore;
        }
    },
    DRAGON_HUNTER("Dragon Hunter", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt to ");
            lore.add(ChatColor.GRAY+"Ender Dragons by "+ChatColor.GREEN+"[8]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    DEPTH_STRIDER("Depth Strider", 3, ItemType.BOOTS, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Reduces how much you are slowed");
            lore.add(ChatColor.GRAY+"in the water by "+ChatColor.GREEN+"[33]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    ENDER_SLAYER("Ender Slayer", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt to");
            lore.add(ChatColor.GRAY+"Endermen and Ender");
            lore.add(ChatColor.GRAY+"Dragons by "+ChatColor.GREEN+"[12]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    EXECUTE("Execute", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt by "+ChatColor.GREEN+"[0.2]%");
            lore.add(ChatColor.GRAY+"for each percent of health");
            lore.add(ChatColor.GRAY+"missing on your target.");
            this.desc = lore;
        }
    },
    EXPERIENCE("Experience", 4, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants a "+ChatColor.GREEN+"[12.5]%"+ChatColor.GRAY+" chance for");
            lore.add(ChatColor.GRAY+"mobs and ores to drop double");
            lore.add(ChatColor.GRAY+"experience.");
            this.desc = lore;
        }
    },
    FIRE_ASPECT("Fire Aspect", 2, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Ignites your enemies for "+ChatColor.GREEN+"3s"+ChatColor.GRAY+",");
            lore.add(ChatColor.GRAY+"dealing "+ChatColor.GREEN+"[50]%"+ChatColor.GRAY+" of your weapon");
            lore.add(ChatColor.GRAY+"damage per second.");
            this.desc = lore;
        }
    },
    FIRST_STRIKE("First Strike", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases melee damage dealt by");
            lore.add(ChatColor.GREEN+"[25]%"+ChatColor.GRAY+" for the first hit on a");
            lore.add(ChatColor.GRAY+"mob.");
            this.desc = lore;
        }
    },
    FEATHER_FALLING("Feather Falling", 5, ItemType.BOOTS, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases how high you can fall");
            lore.add(ChatColor.GRAY+"before taking fall damage by");
            lore.add(ChatColor.GREEN+"[1]"+ChatColor.GRAY+" and reduces fall damage by");
            lore.add(ChatColor.GREEN+"[5]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    FIRE_PROTECTION("Fire Protection", 7, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+"+ChatColor.WHITE+"[2] ❂ True Defense");
            lore.add(ChatColor.GRAY+"against fire and lava.");
            this.desc = lore;
        }
    },
    FROST_WALKER("Frost Walker", 2, ItemType.BOOTS, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Ice blocks will be created below");
            lore.add(ChatColor.GRAY+"when you walk above water in");
            lore.add(ChatColor.GRAY+"a radius of "+ChatColor.GREEN+"[1]"+ChatColor.GRAY+" blocks.");
            this.desc = lore;
        }
    },
    GIANT_KILLER("Giant Killer", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt by "+ChatColor.GREEN+"[0.1]%");
            lore.add(ChatColor.GRAY+"for each percent of extra");
            lore.add(ChatColor.GRAY+"health that your target has");
            lore.add(ChatColor.GRAY+"above you up to "+ChatColor.GREEN+"[5]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    GROWTH("Growth", 7, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+[15]"+ChatColor.RED+" ❤ Health"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    IMPALING("Impaling", 3, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt to");
            lore.add(ChatColor.GRAY+"Squids and Guardians by");
            lore.add(ChatColor.GREEN+"[12.5]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    IMPENETRABLE("Impenetrable", 5, ItemType.SHIELD, false) {
        @Override
        public void setObject() {
            this.desc = List.of(
                    "Hits have a "+c.green("[1]%")+" chance to",
                    "deflect off your shield!"
            );
        }
    },
    KNOCKBACK("Knockback", 2, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases knockback by "+ChatColor.GREEN+"[3]");
            lore.add(ChatColor.GRAY+"blocks.");
            this.desc = lore;
        }
    },
    LETHALITY("Lethality", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Reduces the armor of your target");
            lore.add(ChatColor.GRAY+"by "+ChatColor.GREEN+"[3]%"+ChatColor.GRAY+" for "+ChatColor.GOLD+"8"+ChatColor.GRAY+" seconds each");
            lore.add(ChatColor.GRAY+"time you hit them  with melee.");
            lore.add(ChatColor.GRAY+"Stacks up to "+ChatColor.GREEN+"5"+ChatColor.GRAY+" times.");
            this.desc = lore;
        }
    },
    LIFE_STEAL("Life Steal", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Heals for "+ChatColor.GREEN+"[0.5]%"+ChatColor.GRAY+" of your max");
            lore.add(ChatColor.GRAY+"health each time you hit a mob.");
            this.desc = lore;
        }
    },
    LOOTING("Looting", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases the chance of a");
            lore.add(ChatColor.GRAY+"Monster dropping an item by");
            lore.add(ChatColor.GREEN+"[15]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    LUCK("Luck", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases the chance for");
            lore.add(ChatColor.GRAY+"Monsters to drop their armor by");
            lore.add(ChatColor.GREEN+"[5]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    PROSECUTE("Prosecute", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt by "+ChatColor.GREEN+"[0.1]%");
            lore.add(ChatColor.GRAY+"for each percent of health");
            lore.add(ChatColor.GRAY+"your target has up to "+ChatColor.GREEN+"35%");
            lore.add(ChatColor.GRAY+"damage.");
            this.desc = lore;
        }
    },
    PROJECTILE_PROTECTION("Projectile Protection", 7, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+[7] ❈ Defense");
            lore.add(ChatColor.GRAY+"against projectiles.");
            this.desc = lore;
        }
    },
    PROTECTION("Protection", 7, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+[3] ❈ Defense"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    REJUVENATE("Rejuvenate", 5, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases your natural");
            lore.add(ChatColor.GRAY+"regeneration by "+ChatColor.GREEN+"+[2]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    RESPIRATION("Respiration", 3, ItemType.HELMET, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Extends your underwater");
            lore.add(ChatColor.GRAY+"breathing time by "+ChatColor.GREEN+"[15]s"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    RESPITE("Respite", 5, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases your natural");
            lore.add(ChatColor.GRAY+"regeneration by "+ChatColor.GREEN+"+[5]%"+ChatColor.GRAY+" while");
            lore.add(ChatColor.GRAY+"out of combat.");
            this.desc = lore;
        }
    },
    SCAVENGER("Scavenger", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Scavenge "+ChatColor.GOLD+"[0.3] Coins"+ChatColor.GRAY+" per");
            lore.add(ChatColor.GRAY+"monster level on kill.");
            this.desc = lore;
        }
    },
    SHARPNESS("Sharpness", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases melee damage dealt by");
            lore.add(ChatColor.GREEN+"[5]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    SMITE("Smite", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt to");
            lore.add(ChatColor.GRAY+"Skeletons, Withers,");
            lore.add(ChatColor.GRAY+"Zombie Pigmen, and");
            lore.add(ChatColor.GRAY+"Zombies by "+ChatColor.GREEN+"[8]%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    SYPHON("Syphon", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Heals for "+ChatColor.GREEN+"[0.2]%"+ChatColor.GRAY+" of your max");
            lore.add(ChatColor.GRAY+"health per "+ChatColor.BLUE+"100 ☠ Crit Damage");
            lore.add(ChatColor.GRAY+"you deal per hit up, to");
            lore.add(ChatColor.BLUE+"1,000 ☠ Crit Damage"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    SWEEPING_EDGE("Sweeping Edge", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            this.desc = List.of(
                    "Grants "+c.yellow("+[2]% ⚔")+" Attack Speed."
            );
        }
    },
    TELEKINESIS("Telekinesis", 1, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Mob and block drops go directly");
            lore.add(ChatColor.GRAY+"into your inventory.");
            this.desc = lore;
        }
    },
    THUNDERLORD("Thunderlord", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Strikes a Monster with lightning");
            lore.add(ChatColor.GRAY+"every 3 consecutive hits,");
            lore.add(ChatColor.GRAY+"dealing "+ChatColor.GREEN+"+[25]%"+ChatColor.GRAY+" of your Strength");
            lore.add(ChatColor.GRAY+"as damage.");
            this.desc = lore;
        }
    },
    THUNDERBOLT("Thunderbolt", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Strikes Monsters within 2 blocks");
            lore.add(ChatColor.GRAY+"with lightning every 3");
            lore.add(ChatColor.GRAY+"consecutive hits, dealing"+ChatColor.GREEN+" [15]%");
            lore.add(ChatColor.GRAY+"of your Strength as damage.");
            this.desc = lore;
        }
    },
    THORNS("Thorns", 3, ItemType.ARMOR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants a "+ChatColor.GREEN+"50%"+ChatColor.GRAY+" chance to");
            lore.add(ChatColor.GRAY+"rebound "+ChatColor.GREEN+"[3]%"+ChatColor.GRAY+" of the damage dealt");
            lore.add(ChatColor.GRAY+"back at the attacker.");
            this.desc = lore;
        }
    },
    TRUE_PROTECTION("True Protection", 1, ItemType.CHESTPLATE, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.WHITE+"+[5] ❂ True Defense"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    TITAN_KILLER("Titan Killer", 7, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases damage dealt by "+ChatColor.GREEN+"[2]%");
            lore.add(ChatColor.GRAY+"for every 100 defense your");
            lore.add(ChatColor.GRAY+"target has up to "+ChatColor.GREEN+"50%"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    TRIPLE_STRIKE("Triple Strike", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Increases melee damage dealt by");
            lore.add(ChatColor.GREEN+"[10]%"+ChatColor.GRAY+" for the first three hits");
            lore.add(ChatColor.GRAY+"on a mob.");
            this.desc = lore;
        }
    },
    VAMPIRISM("Vampirism", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Heals for "+ChatColor.GREEN+"[0.1]%"+ChatColor.GRAY+" of your missing");
            lore.add(ChatColor.GRAY+"health whenever you kill an");
            lore.add(ChatColor.GRAY+"enemy.");
            this.desc = lore;
        }
    },
    VENOMOUS("Venomous", 6, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Reduces the target's walk speed");
            lore.add(ChatColor.GRAY+"by "+ChatColor.GREEN+"[5]%"+ChatColor.GRAY+" and deals "+ChatColor.DARK_GREEN+"[5]"+ChatColor.GRAY+" damage");
            lore.add(ChatColor.GRAY+"per second. Lasts"+ChatColor.GOLD+" 4"+ChatColor.GRAY+" seconds.");
            this.desc = lore;
        }
    },
    VICIOUS("Vicious", 5, ItemType.SWORD, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.RED+"[1] ⫽ Ferocity"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    SMARTY_PANTS("Smarty Pants", 5, ItemType.LEGGINGS, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.AQUA+"+[5] ✎ Intelligence"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    SUGAR_RUSH("Sugar Rush", 3, ItemType.BOOTS, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Grants "+ChatColor.GREEN+"+"+ChatColor.WHITE+"[2] ✦ Speed"+ChatColor.GRAY+".");
            this.desc = lore;
        }
    },
    FILL("", 0, ItemType.ERROR, false) {
        @Override
        public void setObject() {
            List<String> lore = new ArrayList<>();
            lore.add("");
            this.desc = lore;
        }
    },
    ;
    String name;
    int max;
    @NotNull ItemType type;
    List<String> desc = new ArrayList<>();
    boolean ultimate;
    Enchant(@NotNull String name, int max, @NotNull ItemType type, boolean ultimate) {
        this.name = name;
        this.max = max;
        this.type =type;
        this.ultimate = ultimate;
    }
    public String getName() {
        return name;
    }
    public List<String> getDesc(int level) {
        List<String> lore = new ArrayList<>(desc);
        int j = 0;
        for (String s:lore) {
            if (s.contains("[") && s.contains("]")) {
                String text = s.substring(s.indexOf("["), s.indexOf("]"));
                text = text.replace("[", "");
                text = text.replace("]", "");
                double value = Double.parseDouble(text);
                value*=level;
                String newText = NumberUtilities.addCommas(BigDecimal.valueOf(value), false);
                String finaltext = s.replaceAll(text, newText);
                finaltext = finaltext.replace("[", "");
                finaltext = finaltext.replace("]", "");
                lore.set(j, finaltext);
            }
            j++;
        }
        for (int i = 0; i < lore.size(); i++)
            lore.set(i, c.gray(lore.get(i)));
        return lore;
    }
    public int getMaxValue() {
        return max;
    }
    public static Enchant fromString(String s) {
        for (Enchant en : Enchant.values()) {
            if (en.name().equals(s)) {
                return en;
            }
        }
        return null;
    }
    @NotNull
    public ItemType getType() {
        return type;
    }
    public List<Enchant> getConflicting() {
        List<Enchant> enchants = new ArrayList<>();
        switch (this) {
            case SHARPNESS -> {
                enchants.add(BANE_OF_ARTHROPODS);
                enchants.add(SMITE);
            }
            case SMITE -> {
                enchants.add(BANE_OF_ARTHROPODS);
                enchants.add(SHARPNESS);
            }
            case BANE_OF_ARTHROPODS -> {
                enchants.add(SHARPNESS);
                enchants.add(SMITE);
            }
            case PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case PROJECTILE_PROTECTION -> {
                enchants.add(PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case FIRE_PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(PROTECTION);
                enchants.add(BLAST_PROTECTION);
            }
            case BLAST_PROTECTION -> {
                enchants.add(PROJECTILE_PROTECTION);
                enchants.add(FIRE_PROTECTION);
                enchants.add(PROTECTION);
            }
            case FIRST_STRIKE -> enchants.add(TRIPLE_STRIKE);
            case TRIPLE_STRIKE -> enchants.add(FIRST_STRIKE);
            case GIANT_KILLER -> enchants.add(TITAN_KILLER);
            case TITAN_KILLER -> enchants.add(GIANT_KILLER);
            case LIFE_STEAL -> enchants.add(SYPHON);
            case SYPHON -> enchants.add(LIFE_STEAL);
            case PROSECUTE -> enchants.add(EXECUTE);
            case EXECUTE -> enchants.add(PROSECUTE);
            case RESPITE -> enchants.add(REJUVENATE);
            case REJUVENATE -> enchants.add(RESPITE);
            case FROST_WALKER -> enchants.add(DEPTH_STRIDER);
            case DEPTH_STRIDER -> enchants.add(FROST_WALKER);
            case THUNDERBOLT -> enchants.add(THUNDERLORD);
            case THUNDERLORD -> enchants.add(THUNDERBOLT);
        }
        return enchants;
    }
    public static void setContained() {
        for (Enchant en:Enchant.values()) {
            en.setObject();
        }
    }
}
