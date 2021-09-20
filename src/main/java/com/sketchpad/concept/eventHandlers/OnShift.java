package com.sketchpad.concept.eventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class OnShift implements Listener {
    @EventHandler
    public void morph(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(p.getName())!=null) {
            Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(p.getName());
            assert t != null;
            p.setInvisible(false);
            t.unregister();
        }
    }
}
