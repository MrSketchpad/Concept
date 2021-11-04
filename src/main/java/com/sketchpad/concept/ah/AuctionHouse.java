package com.sketchpad.concept.ah;

import com.sketchpad.concept.items.InventoryItems;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AuctionHouse {
    public static final SkyblockInventory AH_INVENTORY = new SkyblockInventory(36, "Auction House", true);
    public static final HashMap<UUID, PreAuction> auctions = new HashMap<>();

    public static void initialize() {
        AH_INVENTORY.setItem(11, StaticAuctionGUI.AUCTIONS_BROWSER.getItem());
        AH_INVENTORY.setItem(13, StaticAuctionGUI.VIEW_BIDS.getItem());
        AH_INVENTORY.setItem(15, StaticAuctionGUI.CREATE_AUCTION.getItem());
        AH_INVENTORY.setItem(41, StaticAuctionGUI.AUCTION_STATS.getItem());
        AH_INVENTORY.setItem(40, new SkyblockItem(InventoryItems.close()));

        AuctionGUI.auctionDurationPick(0, false);
    }
    public static void openMain(Player p) {
        AH_INVENTORY.open(p);
    }
    public static void openBIN(Player p) {
        if (!AuctionHouse.auctions.containsKey(p.getUniqueId()))
            AuctionHouse.auctions.put(p.getUniqueId(), new PreAuction(3600, 3600, p, null));
        SkyblockInventory inventory = new SkyblockInventory(54, "Create BIN Auction", true);
        if (auctions.get(p.getUniqueId()).item==null) {
            inventory.setItem(13, StaticAuctionGUI.EMPTY_CREATE_ITEM.getItem());
            inventory.setItem(29, StaticAuctionGUI.NO_AUCTION_SELECTED.getItem());
        }
        else {
            inventory.setItem(13, auctions.get(p.getUniqueId()).item);
            inventory.setItem(29, AuctionGUI.enlistAuction(auctions.get(p.getUniqueId()).item, auctions.get(p.getUniqueId()).cost, auctions.get(p.getUniqueId()).durationInSeconds));
        }
        inventory.setItem(33, AuctionGUI.setAuctionDuration(auctions.get(p.getUniqueId()).durationInSeconds));
        inventory.setItem(31, AuctionGUI.setItemPrice(auctions.get(p.getUniqueId()).cost));
        inventory.open(p);
    }
    public static void openDurationSelection(Player p, int selected) {
        SkyblockInventory inventory = new SkyblockInventory(36, "Auction Duration", true);
        inventory.setItem(10, AuctionGUI.auctionDurationPick(1, selected == 1));
        inventory.setItem(11, AuctionGUI.auctionDurationPick(6, selected == 6));
        inventory.setItem(12, AuctionGUI.auctionDurationPick(12, selected == 12));
        inventory.setItem(13, AuctionGUI.auctionDurationPick(24, selected == 24));
        inventory.setItem(14, AuctionGUI.auctionDurationPick(48, selected == 48));
        inventory.open(p);
    }
}
