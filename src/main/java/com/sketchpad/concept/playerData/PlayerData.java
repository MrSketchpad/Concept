package com.sketchpad.concept.playerData;

import com.sketchpad.concept.utilities.items.SkyblockItem;

import java.util.HashMap;

public class PlayerData {
    public double mining;
    public double farming;
    public double foraging;
    public double combat;
    public double fishing;
    public double alchemy;
    public double enchanting;
    public double catacombs;
    public double taming;
    public HashMap<Integer, SkyblockItem> inventory;
    public PlayerData(double mining, double farming, double foraging, double combat, double fishing, double alchemy, double enchanting, double taming,
                      HashMap<Integer, SkyblockItem> inventory, double catacombs) {
        this.mining = mining;
        this.farming = farming;
        this.foraging = foraging;
        this.combat = combat;
        this.fishing = fishing;
        this.alchemy = alchemy;
        this.enchanting = enchanting;
        this.taming = taming;
        this.inventory = inventory;
        this.catacombs = catacombs;
    }
    public void setInventory(HashMap<Integer, SkyblockItem> inventory) {
        this.inventory = inventory;
    }
}
