package fr.noobtopia.plugin.look;

import fr.noobtopia.plugin.features.rank.Rank;
import fr.noobtopia.plugin.features.rank.RankManager;
import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class LookJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<Rank> ranks = RankManager.getRanksOf(player);

        if(ranks == null || ranks.size() == 0) RankManager.addRank(player, Rank.PLAYER);
        ranks = RankManager.getRanksOf(player);
        ranks.sort((rank1, rank2) -> rank2.getDisplay() - rank1.getDisplay());
        Rank rank = ranks.get(0);

        player.setPlayerListName(rank.getColor() + rank.getName() + "§f - " + rank.getColor() + player.getName());

        String message = Messages.get("look.join").replaceAll("<<player>>", player.getPlayerListName());
        event.setJoinMessage(message);
    }
}