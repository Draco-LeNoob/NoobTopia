package fr.noobtopia.plugin.utils;

import fr.noobtopia.plugin.home.command.tab.completer.HomeTabCompleter;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class TabCompleters {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin){
        TabCompleters.plugin = plugin;

        register("home", new HomeTabCompleter());
        register("delhome", new HomeTabCompleter());
    }

    private static void register(String command, TabCompleter completer){
        Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(completer);
    }
}