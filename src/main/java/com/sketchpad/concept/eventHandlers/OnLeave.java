package com.sketchpad.concept.eventHandlers;

import com.sketchpad.concept.Concept;
import com.sketchpad.concept.playerData.JsonManager;
import com.sketchpad.concept.playerData.PlayerData;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.net.http.WebSocket;
import java.util.HashMap;

public class OnLeave implements Listener {
    @EventHandler
    public void saveInventory(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        HashMap<Integer, SkyblockItem> items = new HashMap<>();
        for (int i = 0; i<54;i++) {
            ItemStack is = p.getInventory().getItem(i);
            if (is!=null) {
                items.put(i, SkyblockItem.fromItemStack(is));
            }
        }
        PlayerData data = Concept.data.get(p.getUniqueId());
        data.setInventory(items);
        Concept.data.put(p.getUniqueId(), data);
        JsonManager.writeAll(p, data);
    }
}
