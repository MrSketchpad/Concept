package com.sketchpad.concept.commands;

import com.sketchpad.concept.utilities.items.SkyblockItem;
import com.sketchpad.concept.utilities.text.c;
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
                try {
                    SkyblockItem i = SkyblockItem.fromItemStack(p.getInventory().getItemInMainHand());
                    i.setHotPotatoBooks(Integer.parseInt(args[0]));
                    p.getInventory().setItemInMainHand(i.toItemStack(p));
                    return true;
                } catch (Exception e) {
                    p.sendMessage(c.red("Your held item is not a skyblock item!"));
                }
            }
        }
        return false;
    }
}
