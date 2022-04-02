package fr.noobtopia.plugin;

import fr.noobtopia.plugin.economy.Economy;
import fr.noobtopia.plugin.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

public class NoobPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Folders.create();
        Files.create();

        Economy.init();
        Messages.init();

        Listeners.init(this);
        Commands.init(this);
        TabCompleters.init(this);
    }
}