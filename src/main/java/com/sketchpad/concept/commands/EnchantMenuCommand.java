package com.sketchpad.concept.commands;

import com.sketchpad.concept.inventories.SkyblockMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnchantMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            SkyblockMenus.enchantmentMenu(null).open(p);
            return true;
        }
        return false;
    }
}
