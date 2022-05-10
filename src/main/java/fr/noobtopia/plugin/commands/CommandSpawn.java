package fr.noobtopia.plugin.commands;

import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            player.setFlying(false);
            player.teleport(new Location(Bukkit.getWorld("world"), 8, 63, 8));
            player.sendTitle("§6Nouvelle zone", "§cSpawn", 10, 30, 10);
        }

        return true;
    }
}