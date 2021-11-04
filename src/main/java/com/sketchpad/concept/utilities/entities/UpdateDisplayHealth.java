package com.sketchpad.concept.utilities.entities;

import com.sketchpad.concept.Concept;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ConstantConditions")
public class UpdateDisplayHealth {
    public static void update(@NotNull LivingEntity en, @Nullable Player p) {
        String name = en.getPersistentDataContainer().get(Concept.getKey("name"), PersistentDataType.STRING);
        double health = en.getPersistentDataContainer().get(Concept.getKey("health"), PersistentDataType.DOUBLE);
        double maxHealth = en.getPersistentDataContainer().get(Concept.getKey("maxHealth"), PersistentDataType.DOUBLE);
        int level = en.getPersistentDataContainer().get(Concept.getKey("level"), PersistentDataType.INTEGER);
        ChatColor healthColor = ChatColor.GREEN;
        if (health<=maxHealth/50) {
            healthColor = ChatColor.YELLOW;
            if (health<0) {
                health = 0;
                if (p!=null) {
                    en.setKiller(p);
                    en.setHealth(0);
                }
            }
        }
        en.setCustomName(ChatColor.DARK_GRAY+"["+ ChatColor.GRAY+"Lvl "+level+ChatColor.DARK_GRAY+"] "+ChatColor.RED+name+" "+healthColor+(int) health
        +ChatColor.WHITE+"/"+ChatColor.GREEN+(int) maxHealth+ChatColor.RED+"❤");
        en.setCustomNameVisible(true);
    }
}
