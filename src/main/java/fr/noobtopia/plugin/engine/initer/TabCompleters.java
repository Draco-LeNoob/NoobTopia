package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.features.home.command.tabcompleter.HomeTabCompleter;
import org.bukkit.command.TabCompleter;

import java.util.Objects;

public class TabCompleters {
    public static void init(){
        register("home", new HomeTabCompleter());
        register("delhome", new HomeTabCompleter());
    }

    private static void register(String command, TabCompleter completer){
        Objects.requireNonNull(NoobPlugin.instance.getCommand(command)).setTabCompleter(completer);
    }
}