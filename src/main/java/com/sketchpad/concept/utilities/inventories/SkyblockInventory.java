package com.sketchpad.concept.utilities.inventories;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.inventories.SkyblockMenus;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SkyblockInventory {
    private int size;
    private String name;
    private List<SkyblockItem> bonusItems = new ArrayList<>();
    private boolean fill;
    private final HashMap<Integer, SkyblockItem> items = new HashMap<>();
    private final List<Integer> clears = new ArrayList<>();
    public SkyblockInventory(int size, String name, boolean fill) {
        this.size = size;
        this.name = name;
        this.fill = fill;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public void setBonusItems(List<SkyblockItem> bonusItems) {
        this.bonusItems = bonusItems;
    }
    public List<SkyblockItem> getBonusItems() {
        return bonusItems;
    }
    public void addItem(SkyblockItem i) {
        bonusItems.add(i);
    }
    public void setItem(int i, SkyblockItem item) {
        this.items.put(i, item);
    }
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    public boolean isFill() {
        return fill;
    }
    public void clear(int slot) {
        clears.add(slot);
    }
    public void removeClear(int slot) {
        if (clears.contains(slot)) clears.remove((Integer) slot);
    }
    private void addItemWithFiller(Inventory inv, ItemStack newItem) {
        for (int i = 0; i<inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item!=null) {
                if (item.hasItemMeta() && NbtManager.hasNbt(item, PersistentDataType.STRING, "type") && Objects.equals(NbtManager.getNbt(item,
                        PersistentDataType.STRING, "type"), "filler")) {
                    inv.setItem(i, newItem);
                    break;
                }
            } else {
                inv.setItem(i, newItem);
                break;
            }
        }
    }
    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, size, name);

        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = fill.getItemMeta();
        m.setDisplayName(" ");
        m.getPersistentDataContainer().set(Concept.getKey("type"), PersistentDataType.STRING, "filler");
        fill.setItemMeta(m);

        ItemStack filler = new ItemStack(Material.AIR);
        if (isFill()) filler = fill;

        ItemStack[] contents = {new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                filler, filler, filler, filler, filler, filler, filler, new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                filler, filler, filler, filler, filler, filler, filler, new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                filler, filler, filler, filler, filler, filler, filler, new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                filler, filler, filler, filler, filler, filler, filler, new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(),
                new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack(), new SkyblockItem(InventoryItems.menuGlass()).toItemStack()};
        inv.setContents(contents);
        for (int i = 0; i < inv.getSize(); i++) {
            if (items.containsKey(i)) inv.setItem(i, items.get(i).toItemStack());
        }
        for (SkyblockItem i: bonusItems) {
            addItemWithFiller(inv, i.toItemStack());
        }
        for (int i:clears) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        p.openInventory(inv);
    }
}
