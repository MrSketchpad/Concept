package com.sketchpad.concept.ah;

import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.entity.Player;

public class PreAuction {
    public int cost;
    public float durationInSeconds;
    public Player seller;
    public SkyblockItem item;
    public PreAuction(int cost, float durationInSeconds, Player seller, SkyblockItem item) {
        this.cost = cost;
        this.durationInSeconds = durationInSeconds;
        this.seller = seller;
        this.item = item;
    }
}
