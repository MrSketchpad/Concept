package com.sketchpad.concept.commands;

import com.sketchpad.concept.utilities.items.SkyblockItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HpbCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length>0) {
                SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                i.setHotPotatoBooks(Integer.parseInt(args[0]));
                p.getInventory().setItemInMainHand(i.toItemStack());
            }
        }
        return false;
    }
}
