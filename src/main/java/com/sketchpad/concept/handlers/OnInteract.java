package com.sketchpad.concept.handlers;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.sketchpad.concept.Concept;
import com.sketchpad.concept.inventories.SkyblockMenus;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.stats.GetStats;
import com.sketchpad.concept.utilities.abilities.Ability;
import com.sketchpad.concept.utilities.abilities.ExecuteAbility;
import com.sketchpad.concept.utilities.entities.DamageEntity;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import net.minecraft.world.entity.projectile.ProjectileHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class OnInteract implements Listener {
    public static HashMap<UUID, Long> shortbowCooldowns = new HashMap<>();
    @EventHandler
    public void arrowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (!e.getEntity().getPersistentDataContainer().has(
                    Concept.getKey("player"), PersistentDataType.STRING)) {
                e.getProjectile().getPersistentDataContainer().set(Concept.getKey("player"), PersistentDataType.STRING, p.getName());
            }
            Player pa = Bukkit.getPlayer(Objects.requireNonNull(e.getProjectile().getPersistentDataContainer().get(Concept.getKey("player"), PersistentDataType.STRING)));
            assert pa != null;
            if (SkyblockItem.fromItemStack(pa.getInventory().getItemInMainHand()).getType()==ItemType.SHORTBOW) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {
        if (e.getHitEntity()!=null && e.getHitEntity().getType()==EntityType.ARMOR_STAND) {
            e.getHitEntity().remove();
            e.setCancelled(true);
            return;
        }
        if (e.getHitEntity()!=null && e.getHitEntity() instanceof LivingEntity en) {
            if (e.getEntity() instanceof Arrow && e.getEntity().getPersistentDataContainer().has(
                    Concept.getKey("player"), PersistentDataType.STRING)) {
                Player p = Bukkit.getPlayer(Objects.requireNonNull(e.getEntity().getPersistentDataContainer().
                        get(Concept.getKey("player"), PersistentDataType.STRING)));
                DamageEntity.melee(p, en, 0, true);
                e.setCancelled(true);
                en.damage(0, p);
            }
        }
        if (e.getHitBlock()!=null) {
            if (e.getEntity() instanceof Arrow && e.getEntity().getPersistentDataContainer().has(
                    Concept.getKey("shortbow"), PersistentDataType.STRING)) {
                e.getEntity().remove();
            }
        }
    }
    @EventHandler
    public void customMining(PlayerInteractEvent e) {
        if (e.getAction()==Action.LEFT_CLICK_BLOCK && e.getClickedBlock()!=null) {
            Player p = e.getPlayer();
        }
    }
    @EventHandler
    public void customMenus(PlayerInteractEvent e) {
        if (e.getAction()== Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock()!=null) {
                if (e.getClickedBlock().getType() == Material.ENCHANTING_TABLE) {
                    e.setCancelled(true);
                    SkyblockMenus.enchantmentMenu(null).open(e.getPlayer());
                } else if (e.getClickedBlock().getType() == Material.ANVIL) {
                    e.setCancelled(true);
                    SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL).open(e.getPlayer());
                }
            }
        }
    }
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
        Player p = e.getPlayer();
        if (e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            SkyblockItem i = SkyblockItem.fromItemStack(e.getPlayer().getInventory().getItemInMainHand());
            if (i.getType()== ItemType.SHORTBOW) {
                if (e.getAction().isRightClick() || e.getAction().isLeftClick()) {
                    boolean run = false;
                    if (shortbowCooldowns.containsKey(p.getUniqueId())) {
                        if (shortbowCooldowns.get(p.getUniqueId()) - Bukkit.getCurrentTick() < (-20/(1+(GetStats.getPlayer(p).getAttackSpeed()/100)))) {
                            shortbowCooldowns.put(p.getUniqueId(), (long) Bukkit.getCurrentTick());
                            run = true;
                        }
                    } else {
                        shortbowCooldowns.put(p.getUniqueId(), (long) Bukkit.getCurrentTick());
                        run = true;
                    }
                    if (run) {
                        Arrow ar = e.getPlayer().launchProjectile(Arrow.class);
                        ar.setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
                        if (ar.isOnGround()) ar.remove();
                        ar.getPersistentDataContainer().set(Concept.getKey("player"), PersistentDataType.STRING, e.getPlayer().getName());
                        ar.getPersistentDataContainer().set(Concept.getKey("shortbow"), PersistentDataType.STRING, "true");
                        e.setCancelled(true);
                    }
                }
            }
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
