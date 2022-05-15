package fr.noobtopia.plugin;

import fr.noobtopia.plugin.features.economy.Economy;
import fr.noobtopia.plugin.engine.enchantment.CustomEnchantments;
import fr.noobtopia.plugin.engine.initer.*;
import fr.noobtopia.plugin.engine.io.Files;
import fr.noobtopia.plugin.engine.io.Folder;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class NoobPlugin extends JavaPlugin {
    public static NoobPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        // Create necessary files and folders for the plugin
        Folder.create();
        Files.create();

        // Initialize several systems
        Economy.init();
        Messages.init();

        // Register listeners, commands and its tab completers
        Listeners.init();
        Commands.init();
        TabCompleters.init();

        // Remove the unwanted craft
        CraftRemover.removeCrafts();

        // Register custom enchantments
        CustomEnchantments.register();

        // Reload listeners
        ListenerReloader.register();
    }

    public static int random(int a, int b){ return a + (int)(Math.random() * ((b - a) + 1)); }
    public static double distance(Location l1, Location l2){
        return Math.sqrt(
                Math.pow(l2.getX() - l1.getX(), 2) +
                Math.pow(l2.getY() - l1.getY(), 2) +
                Math.pow(l2.getZ() - l1.getZ(), 2)
        );
    }
}