package fr.noobtopia.plugin.utils;

import fr.noobtopia.plugin.economy.InitBalances;
import fr.noobtopia.plugin.look.JoinLeaveListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Listeners {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin){
        Listeners.plugin = plugin;

        register(new InitBalances());
        register(new JoinLeaveListener());
    }

    private static void register(Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}