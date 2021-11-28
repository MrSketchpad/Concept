package com.sketchpad.concept.playerdata;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sketchpad.concept.Concept;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@SuppressWarnings({"unchecked", "UnstableApiUsage"})
public class JsonManager {
    public static void writeAll(Player p, PlayerData d) {
        JSONObject main = new JSONObject();
        main.put("data", new Gson().toJson(d, new TypeToken<PlayerData>() {}.getType()));
        try {
            File file = new File(Concept.instance.getDataFolder()+File.separator+p.getUniqueId()+".json");
            File filePath = new File(Concept.instance.getDataFolder() + File.separator + "Concept");

            filePath.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(main.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Concept.instance.getServer().getConsoleSender().sendMessage(ChatColor.RED+"[Concept] FATAL: Could not write player data.");
        }
    }
    public static PlayerData readAll(Player p) {
        PlayerData data = null;
        try {
            JSONParser parser = new JSONParser();
            File file = new File(Concept.instance.getDataFolder()+File.separator+p.getUniqueId()+".json");
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            data = new Gson().fromJson((String) jsonObject.get("data"), new TypeToken<PlayerData>() {}.getType());

        } catch (Exception e) {
            Concept.instance.getServer().getConsoleSender().sendMessage(ChatColor.RED+"[Concept] FATAL: Could not read player data.");
        }
        return data;
    }
}
