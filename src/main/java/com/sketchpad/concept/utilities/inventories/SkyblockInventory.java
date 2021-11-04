package com.sketchpad.concept.utilities.inventories;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.items.NbtManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
    public void addToNearestEmpty(SkyblockItem i) {
        if (clears.size()>0) {
            int index = clears.get(0);
            clears.remove(0);
            items.put(index, i);
        }
    }
    private void addItemWithFiller(@NotNull Inventory inv, @NotNull ItemStack newItem) {
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
    public Inventory open(@NotNull Player p) {
        Inventory inv = Bukkit.createInventory(null, size, name);

        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = fill.getItemMeta();
        m.setDisplayName(" ");
        m.getPersistentDataContainer().set(Concept.getKey("type"), PersistentDataType.STRING, "filler");
        fill.setItemMeta(m);

        ItemStack filler = new ItemStack(Material.AIR);
        if (isFill()) filler = fill;

        ItemStack glass = new SkyblockItem(InventoryItems.menuGlass()).toItemStack(p);
        List<ItemStack> contents = new LinkedList<>(Arrays.asList(glass, glass, glass, glass, glass, glass, glass, glass, glass, glass,
                filler, filler, filler, filler, filler, filler, filler, glass, glass,
                filler, filler, filler, filler, filler, filler, filler, glass, glass,
                filler, filler, filler, filler, filler, filler, filler, glass, glass,
                filler, filler, filler, filler, filler, filler, filler, glass, glass,
                glass, glass, glass, glass, glass, glass, glass, glass));
        for (int i = 0; i < 54-size; i++) {
            contents.remove(contents.size()-9);
        }
        inv.setContents(contents.toArray(new ItemStack[]{}));
        for (int i = 0; i < inv.getSize(); i++) {
            if (items.containsKey(i)) inv.setItem(i, items.get(i).toItemStack(p));
        }
        for (SkyblockItem i: bonusItems) {
            addItemWithFiller(inv, i.toItemStack(p));
        }
        for (int i:clears) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        p.openInventory(inv);
        return inv;
    }
}
