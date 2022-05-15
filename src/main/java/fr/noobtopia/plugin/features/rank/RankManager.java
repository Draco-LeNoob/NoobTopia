package fr.noobtopia.plugin.features.rank;

import fr.noobtopia.plugin.engine.io.Folder;
import fr.noobtopia.plugin.features.permission.PermissionManager;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RankManager {
    public static File getFolderOf(Player player){
        File folder = new File(Folder.rank, player.getUniqueId().toString());
        if(!folder.exists()) folder.mkdir();

        return folder;
    }

    public static File getFileOf(Player player, Rank rank){ return new File(getFolderOf(player), rank.getId() + ""); }
    public static boolean hasRank(Player player, Rank rank){ return getRanksOf(player).contains(rank); }
    public static void addRank(Player player, Rank rank){
        File file = RankManager.getFileOf(player, rank);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Rank fromId(int id){
        for(Rank rank : Rank.values()){
            if(rank.getId() == id) return rank;
        }

        return null;
    }

    public static List<Rank> getRanksOf(Player player){
        List<Rank> ranks = new ArrayList<>();

        for(File file : getFolderOf(player).listFiles()) {
            Rank rank = RankManager.fromId(Integer.parseInt(file.getName()));

            if(rank != null) ranks.add(RankManager.fromId(Integer.parseInt(file.getName())));
        }

        return ranks;
    }

    public static void applyPermissions(Player player){
        for(Rank rank : getRanksOf(player)){
            if(rank == null) continue;

            for(String permission : rank.getPermissions()){
                PermissionManager.addPermission(player, permission);
            }
        }
    }
}