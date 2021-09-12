package com.sketchpad.concept.commands;

import com.sketchpad.concept.inventories.SkyblockMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkyblockMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            SkyblockMenus.skyblockMenu(p).open(p);
            return true;
        }
        return false;
    }
}
