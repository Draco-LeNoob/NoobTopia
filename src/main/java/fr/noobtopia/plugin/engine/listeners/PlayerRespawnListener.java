package fr.noobtopia.plugin.engine.listeners;

import fr.noobtopia.plugin.engine.commands.CommandSpawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        event.setRespawnLocation(CommandSpawn.location);
    }
}