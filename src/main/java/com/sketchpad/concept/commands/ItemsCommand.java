package com.sketchpad.concept.commands;

import com.sketchpad.concept.items.*;
import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.utilities.inventories.SkyblockInventory;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            SkyblockInventory inv = new SkyblockInventory(54, "Items", true);

            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_TEST.item));
            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_INTELLIGENT_TEST.item));
            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_DRAGONS.item));
            inv.addItem(new SkyblockItem(Armor.TEST.helmet, false));
            inv.addItem(new SkyblockItem(Armor.TEST.chestplate, false));
            inv.addItem(new SkyblockItem(Armor.TEST.leggings, false));
            inv.addItem(new SkyblockItem(Armor.TEST.boots, false));
            inv.addItem(new SkyblockItem(Armor.SUPERIOR_DRAGON.helmet, false));
            inv.addItem(new SkyblockItem(Armor.SUPERIOR_DRAGON.chestplate, false));
            inv.addItem(new SkyblockItem(Armor.SUPERIOR_DRAGON.leggings, false));
            inv.addItem(new SkyblockItem(Armor.SUPERIOR_DRAGON.boots, false));
            inv.addItem(new SkyblockItem(OffHands.ARMAGEDDON.item, false));
            inv.addItem(new SkyblockItem(Sword.RAGNAROK.item, false));
            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_AVERAGE_FORUMS_SUGGESTION.item, false));
            inv.addItem(new SkyblockItem(Bow.JUJU.item, false));
            inv.addItem(new SkyblockItem(Materials.HOT_POTATO_BOOK.item, false));
            inv.addItem(new SkyblockItem(Materials.FUMING_POTATO_BOOK.item, false));
            inv.addItem(new SkyblockItem(Materials.RECOMBOBULATOR.item, false));
            inv.addItem(new SkyblockItem(Reforges.FABLED.getReforge().toBaseItem(), false));
            inv.addItem(new SkyblockItem(Reforges.RENOWNED.getReforge().toBaseItem(), false));
            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_END.item, false));
            inv.addItem(new SkyblockItem(Sword.ASPECT_OF_THE_VOID.item, false));
            inv.addItem(new SkyblockItem(Armor.SKETCHPAD.helmet, false));
            inv.addItem(new SkyblockItem(Armor.SKETCHPAD.chestplate, false));
            inv.addItem(new SkyblockItem(Armor.SKETCHPAD.leggings, false));
            inv.addItem(new SkyblockItem(Armor.SKETCHPAD.boots, false));
            inv.open(p);
            return true;
        } else sender.sendMessage(ChatColor.RED+"This command can only be executed by a player!");
        return false;
    }
}
