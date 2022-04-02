package fr.noobtopia.plugin.utils;

import fr.noobtopia.plugin.home.command.CommandDelHome;
import fr.noobtopia.plugin.home.command.CommandHome;
import fr.noobtopia.plugin.home.command.CommandHomes;
import fr.noobtopia.plugin.home.command.CommandSethome;
import fr.noobtopia.plugin.economy.command.CommandBalance;
import fr.noobtopia.plugin.economy.command.CommandPay;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Commands {
    private static JavaPlugin plugin = null;

    public static void init(JavaPlugin plugin){
        Commands.plugin = plugin;

        register("home", new CommandHome());
        register("sethome", new CommandSethome());
        register("delhome", new CommandDelHome());
        register("homes", new CommandHomes());

        register("balance", new CommandBalance());
        register("pay", new CommandPay());
    }

    private static void register(String command, CommandExecutor executor){
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(executor);
    }
}