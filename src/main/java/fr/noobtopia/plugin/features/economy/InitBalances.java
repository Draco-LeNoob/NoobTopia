package fr.noobtopia.plugin.features.economy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InitBalances implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(Economy.needBalance(event.getPlayer())) Economy.registerBalance(event.getPlayer());
    }
}