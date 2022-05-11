package fr.noobtopia.plugin.features.mob;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class DifficultyChangeDisplay implements Listener {
    private static HashMap<UUID, Integer> levels = new HashMap();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        int difficulty = CustomEntity.getLevelFrom(player);

        if(!levels.containsKey(uuid)) levels.put(uuid, difficulty);
        if(levels.get(uuid).equals(difficulty)) return;

        levels.replace(uuid, difficulty);

        if(difficulty > 0)
            player.sendTitle("§6Nouvelle zone", "§cNiveau §e" + difficulty, 10, 30, 10);
        else
            player.sendTitle("§6Nouvelle zone", "§cSpawn", 10, 30, 10);
    }
}