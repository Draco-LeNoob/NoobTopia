package fr.noobtopia.plugin.look;

import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String message = Messages.get("look.join").replaceAll("<<player>>", player.getName());

        event.setJoinMessage(message);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String message = Messages.get("look.quit").replaceAll("<<player>>", player.getName());

        event.setQuitMessage(message);
    }
}