package com.sketchpad.concept.utilities.abilities;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.events.AbilityActivateEvent;
import com.sketchpad.concept.stats.StatManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.utilities.player.ArmorUtilities;
import com.sketchpad.concept.utilities.time.TimeUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class ExecuteAbility {
    public static HashMap<UUID, HashMap<String, Integer>> playerAbilities = new HashMap<>();
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
                                if ((!e.getPlayer().isSneaking())) {
                                    if (ab.getType()== Ability.Type.FULL_SET) {
                                        if (ArmorUtilities.hasFullSet(p)) run = true;
                                    } else run = true;
                                }
                            }
                        }
                        case LEFT_CLICK -> {
                            if (e.getAction()== Action.LEFT_CLICK_BLOCK || e.getAction()== Action.LEFT_CLICK_AIR) {
                                if ((!e.getPlayer().isSneaking())) {
                                    if (ab.getType()== Ability.Type.FULL_SET) {
                                        if (ArmorUtilities.hasFullSet(p)) run = true;
                                    } else run = true;
                                }
                            }
                        }
                        case SHIFT_LEFT_CLICK -> {
                            if (e.getAction()== Action.LEFT_CLICK_BLOCK || e.getAction()== Action.LEFT_CLICK_AIR) {
                                if (e.getPlayer().isSneaking()) {
                                    if (ab.getType()== Ability.Type.FULL_SET) {
                                        if (ArmorUtilities.hasFullSet(p)) run = true;
                                    } else run = true;
                                }
                            }
                        }
                        case SHIFT_RIGHT_CLICK -> {
                            if (e.getAction()== Action.RIGHT_CLICK_BLOCK || e.getAction()== Action.RIGHT_CLICK_AIR) {
                                if (e.getPlayer().isSneaking()) {
                                    if (ab.getType()== Ability.Type.FULL_SET) {
                                        if (ArmorUtilities.hasFullSet(p)) run = true;
                                    } else run = true;
                                }
                            }
                        }
                    }
                }
                if (run) {
                    if (!ab.isEmpty()) {
                        if (ab.getManaCost()<=StatManager.playerMana.get(p.getUniqueId())) {
                            if (!playerAbilities.containsKey(p.getUniqueId())) {
                                HashMap<String, Integer> times = new HashMap<>();
                                times.put(i.getDisplayName(), Bukkit.getCurrentTick());
                                playerAbilities.put(p.getUniqueId(), times);
                            } else if (!(playerAbilities.get(p.getUniqueId()).containsKey(i.getDisplayName()))) {
                                HashMap<String, Integer> times = playerAbilities.get(p.getUniqueId());
                                times.put(i.getDisplayName(), Bukkit.getCurrentTick());
                                playerAbilities.put(p.getUniqueId(), times);
                            }
                            if (TimeUtilities.secondsPassed(playerAbilities.get(p.getUniqueId()).get(i.getDisplayName()), Bukkit.getCurrentTick())>=ab.getCoolDown()) {
                                AbilityActivateEvent event = new AbilityActivateEvent(StatManager.playerMana.get(p.getUniqueId()),
                                        ab.getManaCost(), i, ab, p);
                                Bukkit.getPluginManager().callEvent(event);
                                if (!event.isCancelled()) {
                                    StatManager.playerMana.put(p.getUniqueId(), StatManager.playerMana.get(p.getUniqueId())-event.getManaConsumed());
                                    HashMap<String, Integer> times = playerAbilities.get(p.getUniqueId());
                                    times.put(i.getDisplayName(), Bukkit.getCurrentTick());
                                    playerAbilities.put(p.getUniqueId(), times);

                                    abilities.put(p.getUniqueId(), new AbilityInfo(ab.getManaCost(), ab.getName(), Bukkit.getCurrentTick(), true));
                                    ab.onUse(p);
                                } else Concept.instance.getServer().getConsoleSender().sendMessage("Ability "+ab.getName()+" executed by "+p.getName()+
                                        " was cancelled.");
                            } else p.sendMessage(ChatColor.RED+"This ability is on cooldown for "+(ab.getCoolDown()-TimeUtilities.secondsPassed(
                                    playerAbilities.get(p.getUniqueId()).get(i.getDisplayName())
                                    , Bukkit.getCurrentTick())) +" seconds!");
                        } else p.sendMessage(ChatColor.RED+"You don't have enough mana!");
                    }
                }
            }
        }
    }
}
