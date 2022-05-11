package fr.noobtopia.plugin.engine.io;

import org.bukkit.Bukkit;

import java.io.File;

public class Folder {
    public static final File server = Bukkit.getWorldContainer();
    public static final File plugins = new File(server, "plugins/");
    public static final File noobtopia = new File(plugins, "NoobTopia/");

    public static final File home = new File(noobtopia, "home/");
    public static final File economy = new File(noobtopia, "economy/");
    public static final File permissions = new File(noobtopia, "permissions/");
    public static final File rank = new File(noobtopia, "rank/");
    public static final File mana = new File(noobtopia, "mana/");

    public static void create(){
        if(!noobtopia.exists()) noobtopia.mkdir();
        if(!home.exists()) home.mkdir();
        if(!economy.exists()) economy.mkdir();
        if(!permissions.exists()) permissions.mkdir();
        if(!rank.exists()) rank.mkdir();
        if(!mana.exists()) mana.mkdir();
    }
}