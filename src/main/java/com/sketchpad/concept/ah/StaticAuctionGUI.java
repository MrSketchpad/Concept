package com.sketchpad.concept.ah;

import com.sketchpad.concept.utilities.items.*;
import com.sketchpad.concept.utilities.text.c;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public enum StaticAuctionGUI implements ItemShell {
    NO_AUCTION_SELECTED {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.red("Create BIN Auction"),
                    Material.RED_TERRACOTTA, ItemType.INVENTORY,
                    List.of(
                            "No item selected!",
                            "Click an item in your inventory",
                            "to select it for this auction."
                    )
            ));
        }
    },
    SET_AUCTION_TIME {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.white("Duration: "),
                    Material.GOLDEN_HORSE_ARMOR, ItemType.INVENTORY,
                    List.of(
                            "Selects it for auction"
                    )
            ));
        }
    },
    EMPTY_CREATE_ITEM {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.yellow("Click an item in your inventory!"),
                    Material.STONE_BUTTON, ItemType.INVENTORY,
                    List.of(
                            "Selects it for auction"
                    )
            ));
        }
    },
    CREATE_AUCTION {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.green("Create Auction"),
                    Material.GOLDEN_HORSE_ARMOR, ItemType.INVENTORY,
                    List.of(
                            "Set your own items on auction",
                            "for other players to purchase.",
                            "",
                            c.yellow("Click to become rich!")
                    )
            ) {
                @Override
                public void onClick(InventoryClickEvent e) {
                    AuctionHouse.openBIN((Player) e.getWhoClicked());

                }
            });
        }
    },
    VIEW_BIDS {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.yellow("View Bids"),
                    Material.GOLDEN_CARROT, ItemType.INVENTORY,
                    List.of(
                            "You don't have any outstanding",
                            "bids.",
                            "",
                            "use the "+c.gold("Auction Browser")+" to",
                            "find some cool items."
                    )
            ));
        }
    },
    AUCTIONS_BROWSER {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.gold("Auctions Browser"),
                    Material.GOLD_BLOCK, ItemType.INVENTORY,
                    List.of(
                            "Find items for sale by players",
                            "across Concept!",
                            "",
                            "Items offered here are for",
                            c.gold("auction")+", meaning you have to",
                            "place the top bid to acquire",
                            "them!",
                            "",
                            c.yellow("Click to browse!")
                    )
            ));
        }
    },
    AUCTION_STATS {
        @Override
        public void setObject() {
            item = new SkyblockItem(new ItemBase(
                    Rarity.COMMON, c.green("Auctions Stats"),
                    Material.PAPER, ItemType.INVENTORY,
                    List.of(
                            "View various statistics about",
                            "you and the Auction House.",
                            "",
                            c.yellow("Click to view!")
                    )
            ));
        }
    },
    ;
    SkyblockItem item;
    public SkyblockItem getItem() {
        return item;
    }
    public static void setContained() {
        for (StaticAuctionGUI gui:values()) {
            gui.setObject();
        }
    }
}
