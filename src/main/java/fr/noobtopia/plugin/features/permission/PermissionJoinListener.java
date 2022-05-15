package fr.noobtopia.plugin.features.permission;

import fr.noobtopia.plugin.features.rank.Rank;
import fr.noobtopia.plugin.features.rank.RankManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PermissionJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!RankManager.hasRank(player, Rank.PLAYER)) RankManager.addRank(player, Rank.PLAYER);

        // TODO check if not freeze, if has been registered and logged on
        RankManager.applyPermissions(player);
    }
}