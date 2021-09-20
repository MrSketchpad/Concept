package com.sketchpad.concept.utilities.damage;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.math.BigDecimal;
import java.util.Random;

public class DisplayDamage {
    public static void melee(LivingEntity en, double damage, boolean crit) {
        Random rand = new Random();
        Location loc = en.getLocation();
        loc.setX(loc.getX()+(rand.nextInt(13)/10f));
        loc.setY(loc.getY()+(rand.nextInt(13)/10f));
        loc.setZ(loc.getZ()+(rand.nextInt(13)/10f));
        ArmorStand damageDisplay = (ArmorStand) en.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        damageDisplay.setVisible(false);
        String displayDamage = NumberUtilities.addCommas(BigDecimal.valueOf(damage), true);
        StringBuilder newDisplay = new StringBuilder();
        String finalDisplay;


        if (crit) {
            int digitIndex = 0;
            for (int i = 0; displayDamage.length()>i; i++) {
                String digit = displayDamage.substring(i, i+1);
                digitIndex++;
                if (digitIndex > 5) {
                    digitIndex = 1;
                }
                var color = switch (digitIndex) {
                    case 2 -> ChatColor.YELLOW;
                    case 3 -> ChatColor.GOLD;
                    case 4, 5 -> ChatColor.RED;
                    default -> ChatColor.WHITE;
                };
                digit = color+digit;
                newDisplay.append(digit);
            }
            finalDisplay = ChatColor.WHITE+"✧"+newDisplay+ChatColor.WHITE+"✧";
        } else {
            finalDisplay = ChatColor.GRAY+""+displayDamage;
        }
        damageDisplay.setCustomName(ChatColor.GRAY+finalDisplay);
        damageDisplay.setCustomNameVisible(true);
        damageDisplay.setGravity(false);
        damageDisplay.setSmall(true);
        Bukkit.getScheduler().runTaskLater(Concept.instance, damageDisplay::remove, 20);
    }
}
