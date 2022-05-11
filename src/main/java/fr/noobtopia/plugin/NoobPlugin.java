package fr.noobtopia.plugin;

import fr.noobtopia.plugin.features.economy.Economy;
import fr.noobtopia.plugin.engine.enchantment.CustomEnchantments;
import fr.noobtopia.plugin.engine.initer.*;
import fr.noobtopia.plugin.engine.io.Files;
import fr.noobtopia.plugin.engine.io.Folder;
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
    }

    public static int random(int a, int b){ return a + (int)(Math.random() * ((b - a) + 1)); }
}