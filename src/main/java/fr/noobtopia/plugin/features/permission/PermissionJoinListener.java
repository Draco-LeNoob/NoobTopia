package fr.noobtopia.plugin.features.permission;

import fr.noobtopia.plugin.features.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PermissionJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!Rank.hasRank(player, Rank.PLAYER)) Rank.addRank(player, Rank.PLAYER);

        // TODO check if not freeze, if has been registered and logged on
        Rank.applyPermissions(player);
    }
}