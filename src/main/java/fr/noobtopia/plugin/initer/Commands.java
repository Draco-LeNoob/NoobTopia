package fr.noobtopia.plugin.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.commands.CommandSpawn;
import fr.noobtopia.plugin.enchantment.commands.CommandGiveExplosive;
import fr.noobtopia.plugin.home.command.CommandDelHome;
import fr.noobtopia.plugin.home.command.CommandHome;
import fr.noobtopia.plugin.home.command.CommandHomes;
import fr.noobtopia.plugin.home.command.CommandSethome;
import fr.noobtopia.plugin.economy.command.CommandBalance;
import fr.noobtopia.plugin.economy.command.CommandPay;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class Commands {
    public static void init(){
        register("spawn", new CommandSpawn());
        register("gex", new CommandGiveExplosive());

        register("home", new CommandHome());
        register("sethome", new CommandSethome());
        register("delhome", new CommandDelHome());
        register("homes", new CommandHomes());

        register("balance", new CommandBalance());
        register("pay", new CommandPay());
    }

    public static Command get(String name){ return Bukkit.getPluginCommand(name); }

    private static void register(String command, CommandExecutor executor){
        Objects.requireNonNull(NoobPlugin.instance.getCommand(command)).setExecutor(executor);
    }
}