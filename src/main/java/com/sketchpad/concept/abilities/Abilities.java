package com.sketchpad.concept.abilities;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Abilities {
    public static void rightClick(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
            case "Aspect of the Test" -> p.getWorld().strikeLightningEffect(p.getLocation());
            case "Aspect of the Intelligent Test" -> {
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
        }
    }
    public static void leftClick(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
        }
    }
    public static void jump(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
            case "Aspect of the Test" -> {
                if (p.getTargetBlock(120)!=null)
                p.teleport(Objects.requireNonNull(p.getTargetBlock(120)).getLocation());
            }
        }
    }
    public static void sneak(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
        }
    }
    public static void shiftLeftClick(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
        }
    }
    public static void shiftRightClick(SkyblockItem item, Player p) {
        switch (item.getDisplayName()) {
            case "Aspect of the Test" -> p.setVelocity(p.getLocation().getDirection().multiply(5));
        }
    }
}
