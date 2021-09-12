package com.sketchpad.concept.commands;

import com.sketchpad.concept.utilities.enchantments.Enchant;
import com.sketchpad.concept.utilities.entities.Creature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GeneralTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (cmd.getName().equals("sbenchant")) {
            if (args.length==1) {
                List<String> completes = new ArrayList<>();
                for (Enchant en: Enchant.values()) {
                    completes.add(en.name());
                }
                return completes;
            }
        }
        else if (cmd.getName().equals("spawncustomentity")) {
            if (args.length==1) {
                List<String> completes = new ArrayList<>();
                for (Creature c: Creature.values()) {
                    completes.add(c.name());
                }
                return completes;
            }
        }
        return null;
    }
}
