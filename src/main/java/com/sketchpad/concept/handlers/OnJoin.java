package com.sketchpad.concept.handlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.playerData.JsonManager;
import com.sketchpad.concept.playerData.PlayerData;
import com.sketchpad.concept.stats.StatManager;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnJoin implements Listener {
    @EventHandler
    public void addIfMissing(PlayerJoinEvent e) {
        Concept.inCombat.put(e.getPlayer().getUniqueId(), 6);
        if (!new File(Concept.instance.getDataFolder() + File.separator + e.getPlayer().getUniqueId() + ".json").exists()) {
            JsonManager.writeAll(e.getPlayer(), new PlayerData(0,0,0,0,0,0,0,0, new HashMap<>(), 0));
        }
        Concept.data.put(e.getPlayer().getUniqueId(), JsonManager.readAll(e.getPlayer()));
        StatManager.initiate(e.getPlayer());
        HashMap<Integer, SkyblockItem> items = JsonManager.readAll(e.getPlayer()).inventory;
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i<54; i++) {
            if (items.containsKey(i)) stacks.add(items.get(i).toItemStack(e.getPlayer()));
            else stacks.add(new ItemStack(Material.AIR));
        }
        stacks.set(8, new SkyblockItem(InventoryItems.skyblockMenu()).toItemStack(e.getPlayer()));
        ItemStack[] contents = {
          stacks.get(0),stacks.get(1),stacks.get(2),stacks.get(3),stacks.get(4),stacks.get(5),stacks.get(6),stacks.get(7),stacks.get(8),
          stacks.get(9),stacks.get(10),stacks.get(11),stacks.get(12),stacks.get(13),stacks.get(14),stacks.get(15),stacks.get(16),stacks.get(17),
          stacks.get(18),stacks.get(19),stacks.get(20),stacks.get(21),stacks.get(22),stacks.get(23),stacks.get(24),stacks.get(25),stacks.get(26),
          stacks.get(27),stacks.get(28),stacks.get(29),stacks.get(30),stacks.get(31),stacks.get(32),stacks.get(33),stacks.get(34),stacks.get(35),
          stacks.get(36),stacks.get(37),stacks.get(38),stacks.get(39),stacks.get(40),
        };
        e.getPlayer().getInventory().setContents(contents);
        e.getPlayer().sendMessage(ChatColor.GREEN+"Welcome, "+e.getPlayer().getName()+"!");
    }
}
