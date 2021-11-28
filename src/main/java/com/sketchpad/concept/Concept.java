package com.sketchpad.concept;

import com.sketchpad.concept.ah.StaticAuctionGUI;
import com.sketchpad.concept.ah.AuctionHouse;
import com.sketchpad.concept.commands.*;
import com.sketchpad.concept.handlers.*;
import com.sketchpad.concept.items.*;
import com.sketchpad.concept.kotlin.KotlinUtils;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.playerdata.JsonManager;
import com.sketchpad.concept.playerdata.PlayerData;
import com.sketchpad.concept.stats.StatManager;
import com.sketchpad.concept.utilities.text.c;
import net.axay.kspigot.main.KSpigot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("unchecked")
public class Concept extends KSpigot {
    public static Concept instance;
    public static @NotNull HashMap<UUID, PlayerData> data = new HashMap<>();
    public static @NotNull HashMap<UUID, Integer> inCombat = new HashMap<>();

    @Override
    public void startup() {
        instance = this;
        Sword.setContained();
        Armor.setContained();
        Reforges.setContained();
        Enchant.setContained();
        OffHands.setContained();
        Bow.setContained();
        Materials.setContained();
        StaticAuctionGUI.setContained();
        AuctionHouse.initialize();

        Objects.requireNonNull(getCommand("enchantmenu")).setExecutor(new EnchantMenuCommand());
        Objects.requireNonNull(getCommand("items")).setExecutor(new ItemsCommand());
        Objects.requireNonNull(getCommand("maxthis")).setExecutor(new MaxThisCommand());
        Objects.requireNonNull(getCommand("sbmenu")).setExecutor(new SkyblockMenuCommand());
        Objects.requireNonNull(getCommand("spawncustomentity")).setExecutor(new SpawnCustomEntityCommand());
        Objects.requireNonNull(getCommand("spawncustomentity")).setTabCompleter(new GeneralTabCompleter());
        Objects.requireNonNull(getCommand("hpb")).setExecutor(new HpbCommand());
        Objects.requireNonNull(getCommand("morph")).setExecutor(new MorphCommand());
        KotlinUtils.registerCommands();

        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnHit(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClose(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnItemPickup(), this);
        getServer().getPluginManager().registerEvents(new OnLeave(), this);
        getServer().getPluginManager().registerEvents(new OnSkyblockHit(), this);
        getServer().getPluginManager().registerEvents(new OnShift(), this);
        // TODO: Creature spawn event
        // getServer().getPluginManager().registerEvents(new OnCreatureSpawn(), this);

        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player p:players) {
            StatManager.initiate(p);
            inCombat.put(p.getUniqueId(), 6);
            data.put(p.getUniqueId(), JsonManager.readAll(p));

            HashMap<Integer, SkyblockItem> items = data.get(p.getUniqueId()).inventory;
            List<ItemStack> stacks = new ArrayList<>();
            for (int i = 0; i<54; i++) {
                if (items.containsKey(i)) {
                    getServer().getConsoleSender().sendMessage("Loaded item "+i+"/54 for player "+p.getName()+": "+items.get(i).getDisplayName());
                    stacks.add(items.get(i).toItemStack(p));
                }
                else stacks.add(new ItemStack(Material.AIR));
            }
            stacks.set(8, new SkyblockItem(InventoryItems.skyblockMenu()).toItemStack(p));
            ItemStack[] contents = {
                    stacks.get(0),stacks.get(1),stacks.get(2),stacks.get(3),stacks.get(4),stacks.get(5),stacks.get(6),stacks.get(7),stacks.get(8),
                    stacks.get(9),stacks.get(10),stacks.get(11),stacks.get(12),stacks.get(13),stacks.get(14),stacks.get(15),stacks.get(16),stacks.get(17),
                    stacks.get(18),stacks.get(19),stacks.get(20),stacks.get(21),stacks.get(22),stacks.get(23),stacks.get(24),stacks.get(25),stacks.get(26),
                    stacks.get(27),stacks.get(28),stacks.get(29),stacks.get(30),stacks.get(31),stacks.get(32),stacks.get(33),stacks.get(34),stacks.get(35),
                    stacks.get(36),stacks.get(37),stacks.get(38),stacks.get(39),stacks.get(40),
            };
            p.getInventory().setContents(contents);
            p.setInvisible(false);
            if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(p.getName())!=null) {
                Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(p.getName());
                assert t != null;
                t.unregister();
                List<String> entries = (List<String>) t.getEntries();
                entries.remove(p.getName());
                Objects.requireNonNull(Bukkit.getEntity(UUID.fromString(entries.get(0)))).remove();
            }
        }
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[Concept] Plugin enabled successfully.");
    }
    @Override
    public void shutdown() {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player p:players) {
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
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[Concept] Plugin disabled successfully.");
    }
    public static @NotNull
    NamespacedKey getKey(@NotNull String name) {
        return new NamespacedKey(instance, name);
    }
    public static void debug(String message) {
        Bukkit.broadcastMessage(c.red("[SERVER]: "+message+" (This is a debug message, ignore it!)"));
    }
    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(c.red("[SERVER]: "+message));
    }
    public static void broadcastMessage(Object message) {
        Bukkit.broadcastMessage(c.red("[SERVER]: "+message));
    }
    public static void broadcastInvisible(String message) {
        instance.getServer().getConsoleSender().sendMessage(message);
    }
}
