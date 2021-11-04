package com.sketchpad.concept.ah;

import com.sketchpad.concept.utilities.formatting.NumberUtilities;
import com.sketchpad.concept.utilities.items.ItemBase;
import com.sketchpad.concept.utilities.items.ItemType;
import com.sketchpad.concept.utilities.items.Rarity;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class AuctionGUI {
    public static final HashMap<String, SkyblockItem> auctionGUIs = new HashMap<>();

    public static @NotNull
    SkyblockItem auctionDurationPick(int hours, boolean selected) {
        ItemBase item = new ItemBase(
                Rarity.COMMON, c.green(NumberUtilities.secondsToTime(hours*3600)),
                Material.TERRACOTTA, ItemType.INVENTORY,
                List.of(
                        "Extra fee: "+c.gold("+"+(15 +((hours)*5))+" coins."),
                        "",
                        c.yellow("Click to pick!")
                )) {
            @Override
            public void onClick(InventoryClickEvent e) {
                AuctionHouse.auctions.put(e.getWhoClicked().getUniqueId(), new PreAuction(AuctionHouse.auctions.get(e.getWhoClicked().getUniqueId()).cost
                        , hours*3600, ((Player) e.getWhoClicked()), AuctionHouse.auctions.get(e.getWhoClicked().getUniqueId()).item));
                AuctionHouse.openBIN((Player) e.getWhoClicked());
            }
        };
        switch (hours) {
            case 1 -> item.setMaterial(Material.RED_TERRACOTTA);
            case 6 -> item.setMaterial(Material.PINK_TERRACOTTA);
            case 12 -> item.setMaterial(Material.ORANGE_TERRACOTTA);
            case 24 -> item.setMaterial(Material.YELLOW_TERRACOTTA);
            case 48 -> item.setMaterial(Material.WHITE_TERRACOTTA);
        }
        item.setAttribute("DURATION_PICK"+hours, "true");
        auctionGUIs.put("DURATION_PICK"+hours, new SkyblockItem(item));
        item.setEnchanted(selected);
        return new SkyblockItem(item);
    }
    public static @NotNull
    SkyblockItem enlistAuction(SkyblockItem item, int cost, float duration) {
        return new SkyblockItem(new ItemBase(
                Rarity.COMMON, c.green("Create BIN Auction"),
                Material.GREEN_TERRACOTTA, ItemType.INVENTORY,
                List.of(
                        "This item will be added to the",
                        "auction house for other players",
                        "to purchase.",
                        "",
                        "Item: "+item.getFullName(),
                        "Auction Duration: "+c.yellow(NumberUtilities.secondsToTime(duration)),
                        "Item Price: "+c.gold(NumberUtilities.addCommas(cost, true)+" coins"),
                        "",
                        "Creation Fee: "+c.gold(NumberUtilities.addCommas((cost/100)+15+((duration/3600)*5), true)),
                        "",
                        c.yellow("Click to submit!")
                )));
    }
    public static @NotNull
    SkyblockItem setItemPrice(int cost) {
        return new SkyblockItem(new ItemBase(
                Rarity.COMMON, c.white("Item price: ")+c.gold(NumberUtilities.addCommas(cost, true)+" coins"),
                Material.CLOCK, ItemType.INVENTORY,
                List.of(
                        "The price at which you want to",
                        "sell this item.",
                        "",
                        "Extra fee: "+c.gold(" +"+cost/100+" coins "+c.yellow("(1%)")),
                        "",
                        c.yellow("Click to edit!")
                )));
    }
    public static @NotNull
    SkyblockItem setAuctionDuration(float durationSeconds) {
        float extraFee = 15+((durationSeconds/3600)*5);
        ItemBase item = new ItemBase(
                Rarity.COMMON, c.white("Duration: ")+c.yellow(NumberUtilities.secondsToTime(durationSeconds)),
                Material.GOLD_INGOT, ItemType.INVENTORY,
                List.of(
                        "How long the item will be up for",
                        "sale.",
                        "",
                        "Extra fee: "+c.gold("+"+((int) extraFee)+" coins."),
                        "",
                        c.yellow("Click to edit!")
                )) {
            @Override
            public void onClick(InventoryClickEvent e) {
                AuctionHouse.openDurationSelection((Player) e.getWhoClicked(), (int) (durationSeconds*3600));
            }
        };
        item.setAttribute("SET_DURATION", "true");
        auctionGUIs.put("SET_DURATION", new SkyblockItem(item));
        return new SkyblockItem(item);
    }
}
