package com.sketchpad.concept.utilities.text;

import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class c {
    public static String intel(int i) {
        return aqua("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ✎ Intelligence");
    }
    public static String strength(int i) {
        return red("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ❁ Strength");
    }
    public static String damage(int i) {
        return red("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ❁ Damage");
    }
    public static String speed(int i) {
        return white("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ✦ Speed");
    }
    public static String defense(int i) {
        return green("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ❈ Defense");
    }
    public static String cc(int i) {
        return blue("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+"% ☣ Crit Chance");
    }
    public static String cd(int i) {
        return blue("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+"% ☠ Crit Damage");
    }
    public static String atckSpeed(int i) {
        return yellow("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+"% ⚔ Attack Speed");
    }
    public static String abilityDmg(int i) {
        return red("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+"% ๑ Ability Damage");
    }
    public static String magicFind(int i) {
        return aqua("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ✯ Magic Find");
    }
    public static String petLuck(int i) {
        return magenta("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ♣ Pet Luck");
    }
    public static String trueDefense(int i) {
        return white("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ❂ True Defense");
    }
    public static String scc(int i) {
        return darkAqua("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" α Sea Creature Chance");
    }
    public static String ferocity(int i) {
        return red("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ⫽ Ferocity");
    }
    public static String miningSpd(int i) {
        return gold("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ⸕ Mining Speed");
    }
    public static String miningFortune(int i) {
        return gold("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ☘ Mining Fortune");
    }
    public static String farmingFortune(int i) {
        return gold("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ☘ Farming Fortune");
    }
    public static String foragingFortune(int i) {
        return gold("+"+NumberUtilities.addCommas(BigDecimal.valueOf(i), false)+" ☘ Foraging Fortune");
    }
    public static String black(String s) {
        s = s.replace("§r", "§0");
        return "§0"+s+"§r";
    }
    public static String darkBlue(String s) {
        s = s.replace("§r", "§1");
        return "§1"+s+"§r";
    }
    public static String darkGreen(String s) {
        s = s.replace("§r", "§2");
        return "§2"+s+"§r";
    }
    public static String darkAqua(String s) {
        s = s.replace("§r", "§3");
        return "§3"+s+"§r";
    }
    public static String darkRed(String s) {
        s = s.replace("§r", "§4");
        return "§4"+s+"§r";
    }
    public static String gold(String s) {
        s = s.replace("§r", "§6");
        return "§6"+s+"§r";
    }
    public static String darkGray(String s) {
        s = s.replace("§r", "§8");
        return "§8"+s+"§r";
    }
    public static String aqua(String s) {
        s = s.replace("§r", "§b");
        return "§b"+s+"§r";
    }
    public static String white(String s) {
        s = s.replace("§r", "§f");
        return "§f"+s+"§r";
    }
    public static String red(String s) {
        s = s.replace("§r", "§c");
        return "§c"+s+"§r";
    }
    public static String green(String s) {
        s = s.replace("§r", "§a");
        return "§a"+s+"§r";
    }
    public static String blue(String s) {
        s = s.replace("§r", "§9");
        return "§9"+s+"§r";
    }
    public static String yellow(String s) {
        s = s.replace("§r", "§e");
        return "§e"+s+"§r";
    }
    public static String purple(String s) {
        s = s.replace("§r", "§5");
        return "§5"+s+"§r";
    }
    public static String magenta(String s) {
        s = s.replace("§r", "§d");
        return "§d"+s+"§r";
    }
    public static String gray(String s) {
        s = s.replace("§r", "§7");
        return "§7"+s+"§r";
    }
    public static String bold(String s) {
        s = s.replace("§r", "§l");
        return "§l"+s+"§r";
    }
    public static String italic(String s) {
        s = s.replace("§r", "§o");
        return "§o"+s+"§r";
    }
    public static String underline(String s) {
        s = s.replace("§r", "§n");
        return "§n"+s+"§r";
    }
    public static String strikethrough(String s) {
        s = s.replace("§r", "§m");
        return "§m"+s+"§r";
    }
    public static String magic(String s) {
        s = s.replace("§r", ChatColor.MAGIC.toString());
        return ChatColor.MAGIC+s+"§r";
    }
    public static String custom(int red, int green, int blue, String s) {
        return Component.text(s).color(TextColor.color(red, green, blue)).toString();
    }
}
