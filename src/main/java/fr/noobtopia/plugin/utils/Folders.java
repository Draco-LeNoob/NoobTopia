package fr.noobtopia.plugin.utils;

import org.bukkit.Bukkit;

import java.io.File;

public class Folders {
    public static final File server = Bukkit.getWorldContainer();
    public static final File plugins = new File(server, "plugins/");
    public static final File noobtopia = new File(plugins, "NoobTopia/");
    public static final File homes = new File(noobtopia, "homes/");
    public static final File economy = new File(noobtopia, "economy/");
    public static final File login = new File(noobtopia, "login/");

    public static void create(){
        if(!noobtopia.exists()) noobtopia.mkdir();
        if(!homes.exists()) homes.mkdir();
        if(!economy.exists()) economy.mkdir();
        if(!login.exists()) login.mkdir();
    }
}