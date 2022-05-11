package fr.noobtopia.plugin.features.rank;

import fr.noobtopia.plugin.engine.io.Folder;
import fr.noobtopia.plugin.features.permission.PermissionManager;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rank {
    public static final Rank PLAYER = new Rank(
            "§6", "Joueur", 0, 0,
            PermissionManager.TRAVELER,
            PermissionManager.ECONOMY,
            PermissionManager.MANA
    );

    private String color;
    private String name;
    private int id;
    private int display;
    private List<String> permissions;

    public Rank(String color, String name, int id, int display, String... permissions){
        this.color = color;
        this.name = name;
        this.id = id;
        this.display = display;
        this.permissions = List.of(permissions);
    }

    public String getName(){ return name; }
    public int getId(){ return id; }
    public int getDisplay(){ return display; }
    public String getColor(){ return color; }
    public List<String> getPermissions(){ return List.copyOf(permissions); }

    public static File getFolderOf(Player player){
        File folder = new File(Folder.rank, player.getUniqueId().toString());
        if(!folder.exists()) folder.mkdir();

        return folder;
    }

    public static File getFileOf(Player player, Rank rank){ return new File(getFolderOf(player), rank.getId() + ""); }
    public static boolean hasRank(Player player, Rank rank){ return getRanksOf(player).contains(rank); }
    public static void addRank(Player player, Rank rank){
        File file = Rank.getFileOf(player, rank);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Rank fromString(String rank){
        return switch(rank.toLowerCase()) {
            case "player", "joueur" -> Rank.PLAYER;
            default -> null;
        };
    }

    public static final Rank fromId(int id){
        return switch(id) {
            case 0 -> Rank.PLAYER;
            default -> null;
        };
    }

    public static List<Rank> getRanksOf(Player player){
        List<Rank> ranks = new ArrayList<>();

        for(File file : getFolderOf(player).listFiles()) {
            Rank rank = Rank.fromId(Integer.parseInt(file.getName()));

            if(rank != null) ranks.add(Rank.fromId(Integer.parseInt(file.getName())));
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