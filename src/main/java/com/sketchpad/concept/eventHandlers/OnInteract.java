package com.sketchpad.concept.eventHandlers;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.sketchpad.concept.inventories.SkyblockMenus;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.abilities.ExecuteAbility;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class OnInteract implements Listener {
    @EventHandler
    public void shiftAbilities(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            if (e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
                SkyblockItem i = SkyblockItem.fromItemStack(e.getPlayer().getInventory().getItemInMainHand());
                for (Ability ab:i.getAbilities()) {
                    if (!ab.isEmpty()) {
                        if (ab.getAction()== Ability.Action.SNEAK) {
                            ExecuteAbility.execute(e.getPlayer(), null, false, true);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void jumpAbilities(PlayerJumpEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            SkyblockItem i = SkyblockItem.fromItemStack(e.getPlayer().getInventory().getItemInMainHand());
            for (Ability ab:i.getAbilities()) {
                if (!ab.isEmpty()) {
                    if (ab.getAction()== Ability.Action.JUMP) {
                        ExecuteAbility.execute(e.getPlayer(), null, true, false);
                    }
                }
            }
        }
    }
    @EventHandler
    public void abilities(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            SkyblockItem i = SkyblockItem.fromItemStack(e.getPlayer().getInventory().getItemInMainHand());
            ExecuteAbility.execute(e.getPlayer(), e, false, false);
        }
    }
    @EventHandler
    public void sbMenu(PlayerInteractEvent e) {
        if (e.getItem()!=null && e.getItem().equals(new SkyblockItem(InventoryItems.skyblockMenu()).toItemStack())) {
            SkyblockMenus.skyblockMenu(e.getPlayer()).open(e.getPlayer());
        }
    }
}
