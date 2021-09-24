package com.sketchpad.concept.handlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.events.AbilityActivateEvent;
import com.sketchpad.concept.inventories.SkyblockMenus;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.items.Sword;
import com.sketchpad.concept.utilities.anvil.AnvilUtilities;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("ConstantConditions")
public class MenuHandler implements Listener {
    public static @NotNull
    HashMap<UUID, SkyblockItem> enchants = new HashMap<>();
    HashMap<UUID, SkyblockItem> leftAnvil = new HashMap<>();
    HashMap<UUID, SkyblockItem> rightAnvil = new HashMap<>();
    @EventHandler
    public void testAbility(AbilityActivateEvent e) {

    }
    @EventHandler
    public void backArrow(InventoryClickEvent e) {
        if (e.getCurrentItem()!=null && e.getCurrentItem().equals(new SkyblockItem(InventoryItems.goBack()).toItemStack())) {
            switch (e.getView().getTitle()) {
                case "Choose Level" -> {
                    SkyblockItem item = enchants.get(e.getWhoClicked().getUniqueId());
                    SkyblockInventory inv = SkyblockMenus.enchantmentMenu(item.getType());
                    inv.removeClear(19);
                    inv.setItem(19, item);
                    inv.open((Player) e.getWhoClicked());
                }
            }
        }
    }
    @EventHandler
    public void enchants(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Choose Level")) {
            if (e.getCurrentItem()!=null && e.getCurrentItem().hasItemMeta()) {
                ItemStack i = e.getCurrentItem();
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("enchant"), PersistentDataType.STRING)) {
                    e.setCancelled(true);
                    int lvl = i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("enchlvl"), PersistentDataType.INTEGER);
                    String ench = i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("enchant"), PersistentDataType.STRING);
                    SkyblockItem item = enchants.get(p.getUniqueId());

                    Enchant en = Enchant.valueOf(ench);
                    boolean addNew = true;
                    if (item.getEnchants().enchants.containsKey(en) && item.getEnchants().enchants.get(en) != null
                            && item.getEnchants().enchants.get(en) > 0) {
                        addNew = false;
                        item.getEnchants().set(Enchant.valueOf(ench), 0);
                        p.sendMessage(ChatColor.RED+"You removed "+Enchant.valueOf(ench).getName()+" from your "+
                                item.getRarity().getColor()+item.getDisplayName()+ChatColor.RED+"!");

                    } else {
                        for (Enchant ee: en.getConflicting()) {
                            if (item.getEnchants().enchants.containsKey(ee) && item.getEnchants().enchants.get(ee) != null &&
                                    item.getEnchants().enchants.get(ee) > 0) {
                                addNew = false;
                                item.getEnchants().set(ee, 0);
                                item.getEnchants().set(Enchant.valueOf(ench), lvl);
                                p.sendMessage(ChatColor.GREEN+"You applied "+Enchant.valueOf(ench).getName()+" "+ NumberUtilities.toRomanNumeral(lvl)+" to your "+
                                        item.getRarity().getColor()+item.getDisplayName()+ChatColor.GREEN+"!");
                            }
                        }
                    }
                    if (addNew) item.getEnchants().set(Enchant.valueOf(ench), lvl);

                    SkyblockInventory inv = SkyblockMenus.enchantmentMenu(item.getType());
                    inv.removeClear(19);
                    inv.setItem(19, item);
                    inv.open(p);
                    if (addNew) p.sendMessage(ChatColor.GREEN+"You applied "+Enchant.valueOf(ench).getName()+" "+ NumberUtilities.toRomanNumeral(lvl)+" to your "+
                            item.getRarity().getColor()+item.getDisplayName()+ChatColor.GREEN+"!");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                }
            }
        }
        else if (e.getView().getTitle().equals("Enchant Item")) {
            if (e.getCurrentItem()!=null && e.getCurrentItem().hasItemMeta()) {
                ItemStack i = e.getCurrentItem();
                if (i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("enchant"), PersistentDataType.STRING)) {
                    e.setCancelled(true);
                    SkyblockMenus.chooseEnchantLevelMenu(enchants.get(p.getUniqueId()).getEnchants(),
                            Enchant.valueOf(i.getItemMeta().getPersistentDataContainer().
                            get(Concept.getKey("enchant"), PersistentDataType.STRING))).open(p);
                }
            }
            if (e.getSlot()==19) {
                SkyblockItem i = null;
                ItemStack it = null;
                if (e.getCursor()!=null && e.getCursor().hasItemMeta()) {
                    it = e.getCursor();
                    i = SkyblockItem.fromItemStack(e.getCursor());
                }
                else if (e.getCurrentItem()!=null && e.getCurrentItem().hasItemMeta()) {
                    it = e.getCurrentItem();
                    i = SkyblockItem.fromItemStack(e.getCurrentItem());
                }
                if (i!=null) {
                    if (Objects.requireNonNull(e.getClickedInventory()).getItem(19)!=null) {
                        SkyblockInventory inv = SkyblockMenus.enchantmentMenu(null);
                        inv.open(p);
                        e.setCursor(it);
                        enchants.remove(p.getUniqueId());
                    } else {
                        e.setCurrentItem(new ItemStack(Material.AIR));
                        e.setCursor(new ItemStack(Material.AIR));
                        SkyblockInventory inv = SkyblockMenus.enchantmentMenu(i.getType());
                        inv.removeClear(19);
                        inv.setItem(19, i);
                        inv.open(p);
                        enchants.put(p.getUniqueId(), i);
                    }
                }
            }
        }
    }
    @EventHandler
    public void anvil(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCursor()!=null) {
            if (e.getView().getTitle().equals("Anvil")) {
                if (e.getSlot()==13 && e.getClickedInventory().getItem(13)!=null &&
                !e.getClickedInventory().getItem(13).equals(new SkyblockItem(InventoryItems.centerReforgeItem()).toItemStack())) {
                    if (e.getClickedInventory().getItem(53).getType()==Material.GREEN_STAINED_GLASS_PANE) {
                        e.setCancelled(true);
                    } else {
                        ItemStack it = (e.getCurrentItem());
                        Inventory inven = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL).open(p);
                        inven.setItem(33, e.getClickedInventory().getItem(33));
                        e.setCursor(it);
                    }
                }
                if (e.getSlot()==33) {
                    SkyblockItem i = null;
                    ItemStack it = null;
                    if (e.getCursor() != null && e.getCursor().hasItemMeta()) {
                        it = e.getCursor();
                        i = SkyblockItem.fromItemStack(e.getCursor());
                    } else if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
                        it = e.getCurrentItem();
                        i = SkyblockItem.fromItemStack(e.getCurrentItem());
                    }
                    boolean right = e.getClickedInventory().getItem(29) != null;
                    if (i != null) {
                        SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL);
                        SkyblockInventory inv;
                        if (right) {
                            if (!i.getType().isUpgrade()) {
                                if (SkyblockItem.fromItemStack(e.getClickedInventory().getItem(29)).getBase()!=i.getBase()) {
                                    p.sendMessage(ChatColor.RED+"You cannot sacrifice this item!");
                                    return;
                                }
                            }
                        }
                        if (Objects.requireNonNull(e.getClickedInventory()).getItem(33) != null) {
                            inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL);
                            if (right) {
                                inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.LEFT_SUCCESS);
                                inv.removeClear(29);
                                inv.setItem(29, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(29)));
                            }
                            inv.open(p);
                            e.setCursor(it);
                        } else {
                            e.setCurrentItem(new ItemStack(Material.AIR));
                            e.setCursor(new ItemStack(Material.AIR));
                            inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.RIGHT_SUCCESS);
                            if (right) {
                                inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.SUCCESS);
                                inv.removeClear(29);
                                inv.setItem(29, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(29)));
                                if (AnvilUtilities.evaluateUpgrade(SkyblockItem.fromItemStack(e.getClickedInventory().getItem(29)), i,p,false) .applied) {
                                    inv.setItem(13, AnvilUtilities.evaluateUpgrade(SkyblockItem.fromItemStack(e.getClickedInventory().getItem(29)), i,p,false).item);
                                }
                            }
                            inv.removeClear(33);
                            inv.setItem(33, i);
                            inv.open(p);
                        }
                    }
                } else if (e.getSlot()==29) {
                    SkyblockItem i = null;
                    ItemStack it = null;
                    if (e.getCursor() != null && e.getCursor().hasItemMeta()) {
                        it = e.getCursor();
                        i = SkyblockItem.fromItemStack(e.getCursor());
                    } else if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
                        it = e.getCurrentItem();
                        i = SkyblockItem.fromItemStack(e.getCurrentItem());
                    }
                    boolean right = e.getClickedInventory().getItem(33) != null;
                    if (i != null) {
                        if (Objects.requireNonNull(e.getClickedInventory()).getItem(29) != null) {
                            SkyblockInventory inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL);
                            if (right) {
                                inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.RIGHT_SUCCESS);
                                inv.removeClear(33);
                                inv.setItem(33, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(33)));
                            }
                            inv.open(p);
                            e.setCursor(it);
                        } else {
                            e.setCurrentItem(new ItemStack(Material.AIR));
                            e.setCursor(new ItemStack(Material.AIR));
                            SkyblockInventory inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.LEFT_SUCCESS);
                            if (right) {
                                inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.SUCCESS);
                                inv.removeClear(33);
                                inv.setItem(33, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(33)));
                                if (AnvilUtilities.evaluateUpgrade(i, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(33)), p, false).applied)
                                    inv.setItem(13, AnvilUtilities.evaluateUpgrade(i, SkyblockItem.fromItemStack(e.getClickedInventory().getItem(33)), p, false).item);
                            }
                            inv.removeClear(29);
                            inv.setItem(29, i);
                            inv.open(p);
                        }
                    }
                }
            }
        }
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (e.getView().getTitle().equals("Anvil")) {
                if (i.equals(new SkyblockItem(InventoryItems.combineItemsReforgeItem()).toItemStack())) {
                    if (e.getClickedInventory().getItem(29)!=null) {
                        ItemStack l = e.getClickedInventory().getItem(29);
                        SkyblockItem left = SkyblockItem.fromItemStack(l);
                        if (e.getClickedInventory().getItem(33)!=null) {
                            ItemStack r = e.getClickedInventory().getItem(33);
                            SkyblockItem right = SkyblockItem.fromItemStack(r);
                            if (right.getType().isUpgrade() || left.getBase()==right.getBase()) {
                                if (AnvilUtilities.evaluateUpgrade(left, right, p, true).applied) {
                                    SkyblockInventory inv = SkyblockMenus.reforgeMenu(SkyblockMenus.ReforgeStatus.FAIL);
                                    inv.setItem(13, AnvilUtilities.evaluateUpgrade(left, right, p, true).item);
                                    ItemStack finalR =  e.getClickedInventory().getItem(33);
                                    finalR.setAmount(finalR.getAmount()-1);
                                    ItemStack finalL =  e.getClickedInventory().getItem(29);
                                    finalL.setAmount(finalL.getAmount()-1);;
                                    Inventory newInv = inv.open(p);
                                    newInv.setItem(29, finalL);
                                    newInv.setItem(33, finalR);
                                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                                    p.sendMessage(ChatColor.GREEN+"You applied "+r.getItemMeta().getDisplayName()+ChatColor.GREEN+"" +
                                            " to "+l.getItemMeta().getDisplayName()+ChatColor.GREEN+"!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void skyblockMenu(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("Skyblock Menu") && e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (i.equals(new SkyblockItem(InventoryItems.reforgeButton()).toItemStack())) SkyblockMenus.reforgeMenu
                    ((SkyblockMenus.ReforgeStatus.FAIL)).open((Player)
            e.getWhoClicked());
        }
    }
    @EventHandler
    public void allMenus(InventoryClickEvent e) {
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();
            if (i.hasItemMeta() && i.getType()!=Material.GRAY_STAINED_GLASS_PANE) {
                if (SkyblockItem.fromItemStack(i).getType()==ItemType.INVENTORY) {
                    e.setCancelled(true);
                }
            }
            if (i.equals(new SkyblockItem(InventoryItems.close()).toItemStack())) {
                e.setCancelled(true);
                p.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
            }
        }
    }
    @EventHandler
    public void itemsInventory(InventoryClickEvent e) {
        if (e.getCurrentItem()!=null) {
            ItemStack i = e.getCurrentItem();
            if (i.hasItemMeta() && NbtManager.hasNbt(i, PersistentDataType.STRING, "name")) {
                if (Objects.equals(NbtManager.getNbt(i, PersistentDataType.STRING, "name"), "Skyblock Menu")) {
                    e.setCancelled(true);
                }
            }
            if (i.equals(new SkyblockItem(InventoryItems.menuGlass()).toItemStack()) ||
                    (i.hasItemMeta() && i.getItemMeta().getPersistentDataContainer().has(Concept.getKey("type"), PersistentDataType.STRING) &&
                            Objects.equals(i.getItemMeta().getPersistentDataContainer().get(Concept.getKey("type"), PersistentDataType.STRING), "filler"))){
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equals("Skyblock Menu")) e.setCancelled(true);
            else if (e.getView().getTitle().contains("Items")) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().addItem(i);
                e.getWhoClicked().sendMessage(ChatColor.GREEN+"Gave you "+i.getItemMeta().getDisplayName());
            }
        }
    }
}
