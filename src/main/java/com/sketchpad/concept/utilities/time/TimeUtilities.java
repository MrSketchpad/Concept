package com.sketchpad.concept.utilities.time;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class TimeUtilities {
    public static int secondsPassed(long oldTime, long newTime) {
        return (int) ((oldTime - newTime)/-20);
    }
}
