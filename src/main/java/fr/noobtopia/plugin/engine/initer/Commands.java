package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.engine.commands.CommandSpawn;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.commands.CommandGiveExplosive;
import fr.noobtopia.plugin.features.home.command.CommandDelHome;
import fr.noobtopia.plugin.features.home.command.CommandHome;
import fr.noobtopia.plugin.features.home.command.CommandHomes;
import fr.noobtopia.plugin.features.home.command.CommandSethome;
import fr.noobtopia.plugin.features.economy.command.CommandBalance;
import fr.noobtopia.plugin.features.economy.command.CommandPay;
import fr.noobtopia.plugin.features.mana.commands.CommandMana;
import fr.noobtopia.plugin.features.mob.commands.CommandSummonBoss;
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

        register("mana", new CommandMana());
        register("sb", new CommandSummonBoss());
    }

    public static Command get(String name){ return Bukkit.getPluginCommand(name); }

    private static void register(String command, CommandExecutor executor){
        Objects.requireNonNull(NoobPlugin.instance.getCommand(command)).setExecutor(executor);
    }
}