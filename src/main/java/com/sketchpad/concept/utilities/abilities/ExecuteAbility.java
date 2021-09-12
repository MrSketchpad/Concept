package com.sketchpad.concept.utilities.abilities;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.sketchpad.concept.abilities.Abilities;
import com.sketchpad.concept.stats.StatManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.utilities.time.TimeUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ExecuteAbility {
    public static class AbilityInfo {
        public int manaCost;
        public String name;
        public long time;
        public boolean active;
        public AbilityInfo(int manaCost, String name, long time, boolean active) {
            this.manaCost = manaCost;
            this.name = name;
            this.time = time;
            this.active = active;
        }
    }
    public static HashMap<UUID, AbilityInfo> abilities = new HashMap<>();
    public static void execute(Player p, PlayerInteractEvent e, boolean jump, boolean sneak) {
        if (p.getInventory().getItemInMainHand().hasItemMeta()) {
            SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
            for (int ii = 0; ii<i.getAbilities().size(); ii++) {
                Ability ab = i.getAbilities().get(ii);
                boolean run = false;
                if (jump||sneak) {
                    if (jump && ab.getAction()==Ability.Action.JUMP) {
                        run = true;
                    }
                    else if (sneak && ab.getAction()==Ability.Action.SNEAK) {
                        run = true;
                    }
                } else {
                    switch (ab.getAction()) {
                        case RIGHT_CLICK -> {
                            if (e.getAction()== Action.RIGHT_CLICK_AIR || e.getAction()== Action.RIGHT_CLICK_BLOCK) {
                                if (!e.getPlayer().isSneaking()) run = true;
                            }
                        }
                        case LEFT_CLICK -> {
                            if (e.getAction()== Action.LEFT_CLICK_BLOCK || e.getAction()== Action.LEFT_CLICK_AIR) {
                                if (!e.getPlayer().isSneaking()) run = true;
                            }
                        }
                        case SHIFT_LEFT_CLICK -> {
                            if (e.getAction()== Action.LEFT_CLICK_BLOCK || e.getAction()== Action.LEFT_CLICK_AIR) {
                                if (e.getPlayer().isSneaking()) {
                                    run = true;
                                }
                            }
                        }
                        case SHIFT_RIGHT_CLICK -> {
                            if (e.getAction()== Action.RIGHT_CLICK_BLOCK || e.getAction()== Action.RIGHT_CLICK_AIR) {
                                if (e.getPlayer().isSneaking()) {
                                    run = true;
                                }
                            }
                        }
                    }
                }
                if (run) {
                    if (!ab.isEmpty()) {
                        if (ab.getManaCost()<=StatManager.playerMana.get(p.getUniqueId())) {
                            if (ab.getTimeUsed()==0) {
                                ab.setTimeUsed(p.getWorld().getGameTime());
                                List<Ability> abs = i.getAbilities();
                                abs.set(ii, ab);
                                i.setAbilities(abs);
                                p.getInventory().setItemInMainHand(i.toItemStack());
                            }
                            if (TimeUtilities.secondsPassed(ab.getTimeUsed(), p.getWorld().getGameTime())>=ab.getCoolDown() || !abilities.containsKey(p.getUniqueId())) {
                                StatManager.playerMana.put(p.getUniqueId(), StatManager.playerMana.get(p.getUniqueId())-ab.getManaCost());
                                ab.setTimeUsed(p.getWorld().getGameTime());

                                List<Ability> abs = i.getAbilities();
                                abs.set(ii, ab);
                                i.setAbilities(abs);
                                p.getInventory().setItemInMainHand(i.toItemStack());

                                abilities.put(p.getUniqueId(), new AbilityInfo(ab.getManaCost(), ab.getName(), p.getWorld().getGameTime(), true));
                                switch (ab.getAction()) {
                                    case RIGHT_CLICK -> {
                                        Abilities.rightClick(i, p);
                                    }
                                    case LEFT_CLICK -> Abilities.leftClick(i, p);
                                    case JUMP -> Abilities.jump(i, p);
                                    case SNEAK -> Abilities.sneak(i, p);
                                    case SHIFT_RIGHT_CLICK -> {
                                        Abilities.shiftRightClick(i, p);
                                    }
                                    case SHIFT_LEFT_CLICK -> {
                                        Abilities.shiftLeftClick(i, p);
                                    }
                                }
                            } else p.sendMessage(ChatColor.RED+"This ability is on cooldown for "+(ab.getCoolDown()-TimeUtilities.secondsPassed(ab.getTimeUsed()
                                    , p.getWorld().getGameTime())) +" seconds!");
                        } else p.sendMessage(ChatColor.RED+"You don't have enough mana!");
                    }
                }
            }
        }
    }
}
