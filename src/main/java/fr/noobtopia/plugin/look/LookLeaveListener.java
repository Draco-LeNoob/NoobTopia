package fr.noobtopia.plugin.look;

import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LookLeaveListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String message = Messages.get("look.quit").replaceAll("<<player>>", player.getName());

        event.setQuitMessage(message);
    }
}