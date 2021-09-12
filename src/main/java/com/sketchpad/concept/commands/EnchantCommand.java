package com.sketchpad.concept.commands;

import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.enchantments.SkyblockEnchants;
import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EnchantCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length>1) {
                SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                i.getEnchants().set(Enchant.fromString(args[0]), Integer.parseInt(args[1]));
                p.getInventory().setItemInMainHand(i.toItemStack());
                return true;
            } else p.sendMessage(ChatColor.RED+"Please specify enchant and level!");
        } else sender.sendMessage(ChatColor.RED+"This command can only be executed by a player!");
        return false;
    }
}
