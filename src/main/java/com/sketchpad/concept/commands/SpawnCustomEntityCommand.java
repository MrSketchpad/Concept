package com.sketchpad.concept.commands;

import com.sketchpad.concept.utilities.entities.Creature;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCustomEntityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length>0) {
                for (Creature c:Creature.values()) {
                    if (c.name().equals(args[0])) {
                        assert c.getMob() != null;
                        c.getMob().spawn(p.getLocation(), p);
                        p.sendMessage(ChatColor.GREEN+"Spawned "+c.getMob().getName()+"!");
                    }
                }
            } else p.sendMessage(ChatColor.RED+"Specify a mob to summon!");
        } else sender.sendMessage(ChatColor.RED+"This command can only be executed by a player!");
        return false;
    }
}
