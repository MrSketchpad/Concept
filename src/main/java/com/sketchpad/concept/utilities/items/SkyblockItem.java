package com.sketchpad.concept.utilities.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.items.Armor;
import com.sketchpad.concept.items.Sword;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.stats.SkyblockStats;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.formatting.Number;
import com.sketchpad.concept.utilities.reforges.Reforge;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("ConstantConditions")
public class SkyblockItem {
    boolean recombobulated = false;
    ItemBase base;
    SkyblockEnchants enchants = new SkyblockEnchants();
    Reforge reforge = new Reforge(
            SkyblockStats.getEmpty(), SkyblockStats.getEmpty(), SkyblockStats.getEmpty(),
            SkyblockStats.getEmpty(),SkyblockStats.getEmpty(),SkyblockStats.getEmpty(),
            ItemType.ALL, "", 0, Rarity.COMMON, Material.STONE,
            new ArrayList<>(), Rarity.COMMON);
    int hotPotatoBooks = 0;

    public SkyblockItem(@NotNull ItemBase base) {
        this.base = base;
    }
    public SkyblockItem(@NotNull ItemBase base, boolean recombobulated) {
        this.base = base;
        this.recombobulated = recombobulated;
    }
    public SkyblockItem(@NotNull ItemBase base, boolean recombobulated, Reforge reforge) {
        this.base = base;
        this.recombobulated = recombobulated;
        this.reforge = reforge;
    }
    public @NotNull ItemStack toItemStack() {
        for (Sword sw: Sword.values()) if (sw.equals(base)) base = sw.item;
        for (Armor ar:Armor.values()) if (ar.equals(base, 0)) base = ar.getItems().get(0);
        for (Armor ar:Armor.values()) if (ar.equals(base, 1)) base = ar.getItems().get(1);
        for (Armor ar:Armor.values()) if (ar.equals(base, 2)) base = ar.getItems().get(2);
        for (Armor ar:Armor.values()) if (ar.equals(base, 3)) base = ar.getItems().get(3);
        for (Reforges ar:Reforges.values()) if (ar.equals(base)) base = ar.getReforge().toBaseItem();
        SkyblockStats stats = base.getStats().clone();
        if (enchants.enchants.containsKey(Enchant.CRITICAL) && enchants.enchants.get(Enchant.CRITICAL)!=null &&
                enchants.enchants.get(Enchant.CRITICAL)>0)
        stats.setCritDamage(stats.getCritDamage()+(enchants.enchants.get(Enchant.CRITICAL)*10));
        if (enchants.enchants.containsKey(Enchant.VICIOUS) && enchants.enchants.get(Enchant.VICIOUS)!=null &&
                enchants.enchants.get(Enchant.VICIOUS)>0)
        stats.setFerocity(stats.getFerocity()+(enchants.enchants.get(Enchant.VICIOUS)));
        if (enchants.enchants.containsKey(Enchant.BIG_BRAIN) && enchants.enchants.get(Enchant.BIG_BRAIN)!=null &&
                enchants.enchants.get(Enchant.BIG_BRAIN)>0)
            stats.setIntelligence(stats.getIntelligence()+((enchants.enchants.get(Enchant.BIG_BRAIN))*5));
        if (enchants.enchants.containsKey(Enchant.TRUE_PROTECTION) && enchants.enchants.get(Enchant.TRUE_PROTECTION)!=null &&
                enchants.enchants.get(Enchant.TRUE_PROTECTION)>0)
            stats.setTrueDefense(stats.getTrueDefense()+((enchants.enchants.get(Enchant.TRUE_PROTECTION))*5));
        if (enchants.enchants.containsKey(Enchant.SMARTY_PANTS) && enchants.enchants.get(Enchant.SMARTY_PANTS)!=null &&
                enchants.enchants.get(Enchant.SMARTY_PANTS)>0)
            stats.setIntelligence(stats.getIntelligence()+((enchants.enchants.get(Enchant.SMARTY_PANTS))*5));
        if (enchants.enchants.containsKey(Enchant.GROWTH) && enchants.enchants.get(Enchant.GROWTH)!=null &&
                enchants.enchants.get(Enchant.GROWTH)>0)
            stats.setHealth(stats.getHealth()+((enchants.enchants.get(Enchant.GROWTH))*15));
        if (enchants.enchants.containsKey(Enchant.SUGAR_RUSH) && enchants.enchants.get(Enchant.SUGAR_RUSH)!=null &&
                enchants.enchants.get(Enchant.SUGAR_RUSH)>0)
            stats.setSpeed(stats.getSpeed()+((enchants.enchants.get(Enchant.SUGAR_RUSH))*2));
        if (enchants.enchants.containsKey(Enchant.PROTECTION) && enchants.enchants.get(Enchant.PROTECTION)!=null &&
                enchants.enchants.get(Enchant.PROTECTION)>0)
            stats.setDefense(stats.getDefense()+((enchants.enchants.get(Enchant.PROTECTION))*3));
        if (getType()==ItemType.SWORD || getType()==ItemType.BOW) {
            stats.setDamage(stats.getDamage()+(hotPotatoBooks*2));
            stats.setStrength(stats.getStrength()+(hotPotatoBooks*2));
        } else if (ItemType.ARMOR.getTypes().contains(getType())) {
            stats.setHealth(stats.getHealth()+(hotPotatoBooks*4));
            stats.setDefense(stats.getDefense()+(hotPotatoBooks*2));
        }

        ItemStack i = new ItemStack(base.material);
        EssentialNbts.addAll(i, base.type, base.rarity, base.displayName);
        ItemMeta m = i.getItemMeta();
        m.setUnbreakable(true);
        if (!enchants.getActive().isEmpty()){
            m.addEnchant(Enchantment.IMPALING, 1, true);
            if (enchants.enchants.containsKey(Enchant.KNOCKBACK) && enchants.enchants.get(Enchant.KNOCKBACK)!=null &&
                    enchants.enchants.get(Enchant.KNOCKBACK)>0)
                m.addEnchant(Enchantment.KNOCKBACK, enchants.enchants.get(Enchant.KNOCKBACK), true);
            if (enchants.enchants.containsKey(Enchant.FROST_WALKER) && enchants.enchants.get(Enchant.FROST_WALKER)!=null &&
                    enchants.enchants.get(Enchant.FROST_WALKER)>0)
                m.addEnchant(Enchantment.FROST_WALKER, enchants.enchants.get(Enchant.FROST_WALKER), true);
            if (enchants.enchants.containsKey(Enchant.DEPTH_STRIDER) && enchants.enchants.get(Enchant.DEPTH_STRIDER)!=null &&
                    enchants.enchants.get(Enchant.DEPTH_STRIDER)>0)
                m.addEnchant(Enchantment.DEPTH_STRIDER, enchants.enchants.get(Enchant.DEPTH_STRIDER), true);

        }
        m.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE);

        SkyblockStats reforgeStats = reforge.getStats(getRarity());
        List<String> lore = new ArrayList<>();
        Rarity r = base.rarity;
        if (recombobulated) r = Rarity.getAbove(r);
        String reforgeName = "";
        if (!reforge.getName().equals("")) reforgeName = reforge.getName()+" ";
        m.setDisplayName(r.getColor()+reforgeName+base.displayName);
        if (stats.getDamage()>0 || reforgeStats.getDamage()>0) {
            String bonus = "";
            if (reforgeStats.getDamage()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getDamage()))+")";
            if (hotPotatoBooks>0 && (getType()==ItemType.SWORD || getType()==ItemType.BOW)) bonus = ChatColor.YELLOW+" (+"+(hotPotatoBooks*2)+")"+bonus;
            lore.add(ChatColor.GRAY+"Damage: "+ChatColor.RED+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getDamage()+reforgeStats.getDamage()))+bonus);
        }
        if (stats.getStrength()>0 || reforgeStats.getStrength()>0) {
            String bonus = "";
            if (reforgeStats.getStrength()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getStrength()))+")";
            if (hotPotatoBooks>0 && (getType()==ItemType.SWORD || getType()==ItemType.BOW)) bonus = ChatColor.YELLOW+" (+"+(hotPotatoBooks*2)+")"+bonus;
            lore.add(ChatColor.GRAY+"Strength: "+ChatColor.RED+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getStrength()+reforgeStats.getStrength()))+bonus);
        }
        if (stats.getCritChance()>0 || reforgeStats.getCritChance()>0) {
            String bonus = "";
            if (reforgeStats.getCritChance()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getCritChance()))+"%)";
            lore.add(ChatColor.GRAY+"Crit Chance: "+ChatColor.RED+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getCritChance()+reforgeStats.getCritChance()))+"%"+bonus);
        }
        if (stats.getCritDamage()>0 || reforgeStats.getCritDamage()>0) {
            String bonus = "";
            if (reforgeStats.getCritDamage()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getCritDamage()))+"%)";
            lore.add(ChatColor.GRAY+"Crit Damage: "+ChatColor.RED+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getCritDamage()+reforgeStats.getCritDamage()))+"%"+bonus);
        }
        if (stats.hasOffensive() && stats.hasDefensive()) lore.add("");
        if (stats.getHealth()>0 || reforgeStats.getHealth()>0) {
            String bonus = "";
            if (reforgeStats.getHealth()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getHealth()))+")";
            if (hotPotatoBooks>0 && (ItemType.ARMOR.getTypes().contains(getType()))) bonus = ChatColor.YELLOW+" (+"+(hotPotatoBooks*4)+")"+bonus;
            lore.add(ChatColor.GRAY+"Health: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getHealth()+reforgeStats.getHealth()))+bonus);
        }
        if (stats.getDefense()>0 || reforgeStats.getDefense()>0) {
            String bonus = "";
            if (reforgeStats.getDefense()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getDefense()))+")";
            if (hotPotatoBooks>0 && (ItemType.ARMOR.getTypes().contains(getType()))) bonus = ChatColor.YELLOW+" (+"+(hotPotatoBooks*2)+")"+bonus;
            lore.add(ChatColor.GRAY+"Defense: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getDefense()+reforgeStats.getDefense()))+bonus);
        }
        if (stats.getSpeed()>0 || reforgeStats.getSpeed()>0) {
            String bonus = "";
            if (reforgeStats.getSpeed()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getSpeed()))+")";
            lore.add(ChatColor.GRAY+"Speed: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getSpeed()+reforgeStats.getSpeed()))+bonus);
        }
        if (stats.getIntelligence()>0 || reforgeStats.getIntelligence()>0) {
            String bonus = "";
            if (reforgeStats.getIntelligence()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getIntelligence()))+")";
            lore.add(ChatColor.GRAY+"Intelligence: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getIntelligence()+reforgeStats.getIntelligence()))+bonus);
        }
        if (stats.getTrueDefense()>0 || reforgeStats.getTrueDefense()>0) {
            String bonus = "";
            if (reforgeStats.getTrueDefense()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getTrueDefense()))+")";
            lore.add(ChatColor.GRAY+"True Defense: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getTrueDefense()+reforgeStats.getTrueDefense()))+bonus);
        }
        if (stats.getFerocity()>0 || reforgeStats.getFerocity()>0) {
            String bonus = "";
            if (reforgeStats.getFerocity()>0) bonus = ChatColor.BLUE+" ("+reforge.getName()+" +"+Number.addCommas(BigDecimal.valueOf(reforgeStats.getFerocity()))+")";
            lore.add(ChatColor.GRAY+"Ferocity: "+ChatColor.GREEN+"+"+
                    Number.addCommas(BigDecimal.valueOf(stats.getFerocity()+reforgeStats.getFerocity()))+bonus);
        }
        if (stats.hasOffensive() || stats.hasDefensive()) lore.add("");
        m.getPersistentDataContainer().set(Concept.getKey("loreStart"), PersistentDataType.INTEGER, lore.size());
        List<String> enchantLore = new ArrayList<>();
        StringBuilder enchantBuilder = new StringBuilder();
        int j = 0;
        if (!enchants.isEmpty()) {
            for (Enchant en: Enchant.values()) {
                if (enchants.enchants.containsKey(en)) {
                    if (enchants.enchants.containsKey(en) && enchants.enchants.get(en)!=null) {
                        j++;
                        if (enchants.enchants.get(en)>0) {
                            if (enchants.getActive().size()<10) {
                                lore.add(ChatColor.BLUE+(en.getName())+(" ")+(Number.toRomanNumeral(enchants.getAll().get(en))));
                                if (enchants.getActive().size()<6) {
                                    lore.addAll(en.getDesc(enchants.enchants.get(en)));
                                }
                            } else {
                                if ((enchantBuilder.toString()+ChatColor.BLUE+(en.getName())+(" ")+(Number.toRomanNumeral(enchants.getAll().get(en))+(", "))).length()>40) {
                                    enchantLore.add(enchantBuilder.toString());
                                    enchantBuilder = new StringBuilder();
                                }
                                enchantBuilder.append(ChatColor.BLUE).append(en.getName()).append(" ").append(Number.toRomanNumeral(enchants.getAll().get(en)));
                                if (!(enchants.getActive().size()<=j)) {
                                    enchantBuilder.append(", ");
                                }
                            }
                        }
                    }
                }
            }
            if (!(enchants.getActive().size()<10)) {
                enchantLore.add(enchantBuilder.toString());
                lore.addAll(enchantLore);
            }
            lore.add("");
        }
        for (Ability ab:base.getAbilities()) {
            if (!ab.isEmpty()) {
                lore.add(ab.getType().getText()+ab.getName()+" "+ab.getAction().getText());
                lore.addAll(ab.getLore());
                if (ab.getManaCost()>0) lore.add(ChatColor.DARK_GRAY+"Mana Cost: "+ChatColor.DARK_AQUA+ab.getManaCost());
                if (ab.getCoolDown()>0) lore.add(ChatColor.DARK_GRAY+"Cooldown: "+ChatColor.GREEN+ab.getCoolDown()+"s");
                lore.add("");
            }
        }
        if (!base.lore.isEmpty()) {
            lore.addAll(base.lore);
            if (getType()!=ItemType.INVENTORY) {
                lore.add("");
            }
        }
        if (lore.size()>1) m.getPersistentDataContainer().set(Concept.getKey("loreEnd"), PersistentDataType.INTEGER, lore.size()-1);
        else m.getPersistentDataContainer().set(Concept.getKey("loreEnd"), PersistentDataType.INTEGER, lore.size());
        if (!reforge.getAbility().isEmpty()) {
            lore.add(ChatColor.BLUE+reforge.getName()+" Ability");
            lore.addAll(reforge.getAbility());
            lore.add("");
        }
        if (getType()!=ItemType.INVENTORY) {
            if (!recombobulated) lore.add(r.getColor()+""+ ChatColor.BOLD+r+" "+base.type.name().replace("_", " "));
            else lore.add(r.getColor()+""+ChatColor.BOLD+ChatColor.MAGIC+"E "+ChatColor.RESET+""+r.getColor()+""+ChatColor.BOLD+r+" "+base.type.name().replace("_", " ")+ChatColor.BOLD+ChatColor.MAGIC+" E");
        }
        String recombValue = "n";
        if (recombobulated) recombValue = "y";
        m.getPersistentDataContainer().set(Concept.getKey("recombobulated"), PersistentDataType.STRING, recombValue);
        m.getPersistentDataContainer().set(Concept.getKey("reforge"), PersistentDataType.STRING, reforge.getName());
        m.getPersistentDataContainer().set(Concept.getKey("hpb"), PersistentDataType.INTEGER, hotPotatoBooks);
        if (base.inventoryEnchant!=Enchant.FILL) m.getPersistentDataContainer().set(Concept.getKey("enchant"),
                PersistentDataType.STRING, base.inventoryEnchant.name());
        if (base.inventoryEnchLevel!=0) m.getPersistentDataContainer().set(Concept.getKey("enchlvl"),
                PersistentDataType.INTEGER, base.inventoryEnchLevel);
        m.setLore(lore);
        i.setItemMeta(m);
        NbtManager.addAll(i, stats.add(reforgeStats));
        if (getMaterial()==Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) i.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", base.url));
            try {
                Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                mtd.setAccessible(true);
                mtd.invoke(skullMeta, profile);
            } catch (@NotNull IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            i.setItemMeta(skullMeta);
        } else if (getMaterial()==Material.LEATHER_HELMET || getMaterial()==Material.LEATHER_CHESTPLATE || getMaterial()==Material.LEATHER_LEGGINGS ||
                getMaterial()==Material.LEATHER_BOOTS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
            meta.setColor(base.dye);
            i.setItemMeta(meta);
        }
        EssentialNbts.addEnchants(i, enchants);
        return i;
    }

    public static @NotNull
    SkyblockItem fromItemStack(@NotNull ItemStack i) {
        boolean recombed = Objects.equals(NbtManager.getNbt(i, PersistentDataType.STRING, "recombobulated"), "y");
        Reforge reforge = new Reforge(
                SkyblockStats.getEmpty(), SkyblockStats.getEmpty(), SkyblockStats.getEmpty(),
                SkyblockStats.getEmpty(), SkyblockStats.getEmpty(),SkyblockStats.getEmpty(),
                ItemType.ALL, "", 0, Rarity.COMMON,
                Material.STONE, new ArrayList<>(), Rarity.COMMON);
        int hpb = (Integer) NbtManager.getNbt(i, PersistentDataType.INTEGER, "hpb");
        for (Reforges r:Reforges.values()) {
            if (r.getReforge().getName().equals(NbtManager.getNbt(i, PersistentDataType.STRING, "reforge"))) {
                reforge = r.getReforge();
                break;
            }
        }
        String[] words = i.getType().toString().replace("_", " ").toLowerCase().split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        String name = capitalizeWord.toString().trim();
        ItemBase item = new ItemBase(Rarity.COMMON, name, i.getType(), ItemType.MATERIAL);
        if (Objects.equals(NbtManager.getNbt(i, PersistentDataType.STRING, "type"), "inventory")) item = new ItemBase(Rarity.COMMON, name, i.getType(), ItemType.INVENTORY);
        for (Sword sw: Sword.values()) {
            if (sw.equals(i)) {
                item = sw.getItem();
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 0)) {
                item = ar.getItems().get(0);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 1)) {
                item = ar.getItems().get(1);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 2)) {
                item = ar.getItems().get(2);
                break;
            }
        }
        for (Armor ar:Armor.values()) {
            if (ar.equals(i, 3)) {
                item = ar.getItems().get(3);
                break;
            }
        }
        for (Reforges ar:Reforges.values()) {
            if (ar.equals(i)) {
                item = ar.getReforge().toBaseItem();
                break;
            }
        }
        SkyblockItem newI = new SkyblockItem(item, recombed, reforge);
        newI.setEnchants(SkyblockEnchants.getAll(i));
        newI.setHotPotatoBooks(hpb);
        return newI;
    }

    public String getDisplayName() {
        return base.displayName;
    }
    public Rarity getRarity() {
        if (recombobulated) return Rarity.getAbove(base.rarity);
        else return base.rarity;
    }
    public void setRarity(Rarity rarity) {
        base.rarity = rarity;
    }
    public void setDisplayName(String displayName) {
        base.displayName = displayName;
    }
    public Material getMaterial() {
        return base.material;
    }
    public void setMaterial(Material material) {
        base.material = material;
    }
    public boolean hasStats() {
        return (!base.skyblockStats.equals(SkyblockStats.getPlayer())) ;
    }
    public void setStats(SkyblockStats skyblockStats) {
        base.skyblockStats = skyblockStats;
    }
    public SkyblockStats getStats() {
        return base.skyblockStats;
    }
    public ItemType getType() {
        return base.type;
    }
    public void setType(ItemType type) {
        base.type = type;
    }
    public List<Ability> getAbilities() {
        return base.abilities;
    }
    public void setAbilities(List<Ability> abilities) {
        base.abilities = abilities;
    }
    public List<String> getLore() {
        return base.lore;
    }
    public void setLore(List<String> lore) {
        base.lore = lore;
    }
    public void setRecombobulated(boolean b) {
        this.recombobulated = b;
    }
    public boolean isRecombobulated() {
        return this.recombobulated;
    }
    public ItemBase getBase() {
        return base;
    }
    public SkyblockEnchants getEnchants() {
        return enchants;
    }
    public void setEnchants(SkyblockEnchants enchants) {
        this.enchants = enchants;
    }
    public Reforge getReforge() {
        return reforge;
    }
    public void setReforge(Reforge reforge) {
        this.reforge = reforge;
    }
    public void setHotPotatoBooks(int hotPotatoBooks) {
        this.hotPotatoBooks = hotPotatoBooks;
    }
    public int getHotPotatoBooks() {
        return hotPotatoBooks;
    }
}
