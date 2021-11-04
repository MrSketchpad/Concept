package com.sketchpad.concept.stats;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.events.NaturalRegenEvent;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.abilities.ExecuteAbility;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.utilities.player.DamagePlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class StatManager {
    public static HashMap<UUID, Double> playerHealths = new HashMap<>();
    public static HashMap<UUID, Double> playerMana = new HashMap<>();
    public static HashMap<UUID, SkyblockStats> previous = new HashMap<>();

    public static void runOnce(Player p) {
        playerHealths.putIfAbsent(p.getUniqueId(), GetStats.getPlayer(p).getHealth());
        playerMana.putIfAbsent(p.getUniqueId(), GetStats.getPlayer(p).getIntelligence());
        previous.put(p.getUniqueId(), GetStats.getPlayer(p));
        if (p.getOpenInventory().getTitle().equals("Skyblock Menu")) {
            p.getOpenInventory().getTopInventory().setItem(13, new SkyblockItem(InventoryItems.stats(p)).toItemStack(p));
        }
        SkyblockStats stats = GetStats.getPlayer(p);

        if (previous.get(p.getUniqueId()).getHealth()==playerHealths.get(p.getUniqueId())) playerHealths.put(p.getUniqueId(), stats.getHealth());
        if (previous.get(p.getUniqueId()).getIntelligence()==playerMana.get(p.getUniqueId())) playerMana.put(p.getUniqueId(), stats.getIntelligence());
        if (playerMana.get(p.getUniqueId())> stats.getIntelligence()) playerMana.put(p.getUniqueId(), stats.getIntelligence());

        double speed = stats.getSpeed();
        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed/900);

        String middleText = ChatColor.GREEN+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getDefense()), true)+"❈ Defense";
        if (ExecuteAbility.abilities.containsKey(p.getUniqueId())) {
            ExecuteAbility.AbilityInfo ab = ExecuteAbility.abilities.get(p.getUniqueId());
            if (ab.active && ab.manaCost>0) {
                if (ab.time - p.getWorld().getGameTime() > -60) {
                    middleText = ChatColor.AQUA+"-"+ab.manaCost+" Mana ("+ChatColor.GOLD+ab.name+ChatColor.AQUA+")";
                } else ExecuteAbility.abilities.remove(p.getUniqueId());
            }
        }
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                ChatColor.RED+"   "+ NumberUtilities.addCommas(BigDecimal.valueOf(playerHealths.get(p.getUniqueId())), true) +"/"+
                        NumberUtilities.addCommas(BigDecimal.valueOf(stats.getHealth()), true)+"❤"+
                        "          "+middleText+ChatColor.AQUA+
                        "          "+ NumberUtilities.addCommas(BigDecimal.valueOf(playerMana.get(p.getUniqueId())), true)+"/"+ NumberUtilities.addCommas(BigDecimal.valueOf(
                        stats.getIntelligence()), true)+"✎ Mana"));
        previous.put(p.getUniqueId(), stats);
    }
    public static void initiate(Player p) {
        playerHealths.putIfAbsent(p.getUniqueId(), GetStats.getPlayer(p).getHealth());
        playerMana.putIfAbsent(p.getUniqueId(), GetStats.getPlayer(p).getIntelligence());
        previous.put(p.getUniqueId(), GetStats.getPlayer(p));
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (p.getOpenInventory().getTitle().equals("Skyblock Menu")) {
                    p.getOpenInventory().getTopInventory().setItem(13, new SkyblockItem(InventoryItems.stats(p)).toItemStack(p));
                }
                SkyblockStats stats = GetStats.getPlayer(p);
                double healthRegen = 20;
                double manaRegen = 20;
                healthRegen*=SkyblockEnchants.getAll(p).getHealingBonus(p);
                if (healthRegen<=0) healthRegen = 1;
                if (manaRegen<=0) manaRegen = 1;

                double hpRegened = stats.getHealth()/healthRegen;
                double manaRegened = stats.getIntelligence()/manaRegen;
                NaturalRegenEvent e = new NaturalRegenEvent(GetStats.getPlayer(p), p, hpRegened,
                        manaRegened);
                Bukkit.getPluginManager().callEvent(e);
                hpRegened = e.getHealthIncreased();
                manaRegened = e.getManaIncreased();
                if (!e.isCancelled()) {
                    if (previous.get(p.getUniqueId()).getHealth()==playerHealths.get(p.getUniqueId())) playerHealths.put(p.getUniqueId(), stats.getHealth());
                    if (previous.get(p.getUniqueId()).getIntelligence()==playerMana.get(p.getUniqueId())) playerMana.put(p.getUniqueId(), stats.getIntelligence());
                    playerHealths.put(p.getUniqueId(), playerHealths.get(p.getUniqueId())+hpRegened);

                    if (playerHealths.get(p.getUniqueId())<=0) DamagePlayer.kill(p, "");

                    if (playerHealths.get(p.getUniqueId())> stats.getHealth()) playerHealths.put(p.getUniqueId(), stats.getHealth());
                    playerMana.put(p.getUniqueId(), playerMana.get(p.getUniqueId())+manaRegened);
                    if (playerMana.get(p.getUniqueId())> stats.getIntelligence()) playerMana.put(p.getUniqueId(), stats.getIntelligence());

                    double speed = stats.getSpeed();
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(speed/900);
                }

                String middleText = ChatColor.GREEN+ NumberUtilities.addCommas(BigDecimal.valueOf(stats.getDefense()), true)+"❈ Defense";
                if (ExecuteAbility.abilities.containsKey(p.getUniqueId())) {
                    ExecuteAbility.AbilityInfo ab = ExecuteAbility.abilities.get(p.getUniqueId());
                    if (ab.active && ab.manaCost>0) {
                        if (ab.time - Bukkit.getCurrentTick() > -60) {
                            middleText = ChatColor.AQUA+"-"+ab.manaCost+" Mana ("+ChatColor.GOLD+ab.name+ChatColor.AQUA+")";
                        } else ExecuteAbility.abilities.remove(p.getUniqueId());
                    }
                }

                Concept.inCombat.put(p.getUniqueId(), Concept.inCombat.get(p.getUniqueId())+1);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                        ChatColor.RED+"   "+ NumberUtilities.addCommas(BigDecimal.valueOf(playerHealths.get(p.getUniqueId())), true) +"/"+
                                NumberUtilities.addCommas(BigDecimal.valueOf(stats.getHealth()), true)+"❤"+
                                "          "+middleText+ChatColor.AQUA+
                                "          "+ NumberUtilities.addCommas(BigDecimal.valueOf(playerMana.get(p.getUniqueId())), true)+"/"+ NumberUtilities.addCommas(BigDecimal.valueOf(
                                        stats.getIntelligence()), true)+"✎ Mana"));
                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                    ItemStack it = p.getInventory().getItemInMainHand();
                    SkyblockItem i = SkyblockItem.fromItemStack(it);
                    it.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier
                            ("movementSpeed", 1.6+(stats.getAttackSpeed()/100), AttributeModifier.Operation.ADD_NUMBER));
                }

                double percentageHealth = playerHealths.get(p.getUniqueId())/stats.getHealth();
                p.setHealth(p.getMaxHealth()*percentageHealth);
                previous.put(p.getUniqueId(), stats);
                if (p.getInventory().getItemInMainHand().hasItemMeta())
                    p.getInventory().setItemInMainHand(SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand()).toItemStack(p));
                if (p.getInventory().getItemInOffHand().hasItemMeta())
                    p.getInventory().setItemInOffHand(SkyblockItem.fromItemStack(p.getInventory().getItemInOffHand()).toItemStack(p));
            }
        };
        runnable.runTaskTimer(Concept.instance, 0, 20);
    }
}
