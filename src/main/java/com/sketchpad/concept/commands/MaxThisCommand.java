package com.sketchpad.concept.commands;

import com.sketchpad.concept.reforges.Reforges;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MaxThisCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                SkyblockItem item = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                if (item.getType().isReforgeable()) {
                    switch (item.getType()) {
                        case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> item.setReforge(Reforges.RENOWNED.getReforge());
                        case SWORD -> item.setReforge(Reforges.FABLED.getReforge());
                    }
                }
                item.setRecombobulated(true);
                SkyblockEnchants enchants = SkyblockEnchants.getMax(item.getType());
                item.setEnchants(enchants);
                item.setHotPotatoBooks(15);
                p.getInventory().setItemInMainHand(item.toItemStack());
                p.sendMessage(ChatColor.GREEN+"Maxed out "+item.getDisplayName()+"!");
                return true;
            }
        } else sender.sendMessage(ChatColor.RED+"This command can only be executed by a player!");
        return false;
    }
}
