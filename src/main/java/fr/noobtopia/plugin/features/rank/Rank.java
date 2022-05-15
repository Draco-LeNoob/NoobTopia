package fr.noobtopia.plugin.features.rank;

import fr.noobtopia.plugin.features.permission.PermissionManager;

import java.util.List;

public enum Rank{
    CREATOR(
            "§4", "Dieux Créateur", 200, 200,
            PermissionManager.TRAVELER,
            PermissionManager.ECONOMY,
            PermissionManager.MANA
    ), ADMINISTRATOR(
            "§c", "Administrateur", 100, 100,
            PermissionManager.TRAVELER,
            PermissionManager.ECONOMY,
            PermissionManager.MANA
    ), PLAYER(
            "§6","Joueur",0,0,
            PermissionManager.TRAVELER,
            PermissionManager.ECONOMY,
            PermissionManager.MANA
    );

    private String color;
    private String name;
    private int id;
    private int display;
    private List<String> permissions;

    Rank(String color, String name, int id, int display, String... permissions){
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
}