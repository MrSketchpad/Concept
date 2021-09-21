package com.sketchpad.concept.commands;

import com.sketchpad.concept.Concept;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class MorphCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.getTargetEntity(120)!=null && p.getTargetEntity(120) instanceof LivingEntity en) {
                Team t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(p.getName());
                t.addEntry(en.getUniqueId().toString());
                t.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
                t.addPlayer(p);
                en.setCustomName(p.getDisplayName());
                p.setInvisible(true);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(Concept.instance, () -> {
                    Location loc = p.getLocation();
                    loc.add(p.getLocation().getDirection().multiply(-0.5));
                    loc.setY(p.getLocation().getY());
                    en.teleport(loc);
                },0,1L);
            }
            return true;
        }
        return false;
    }
}
