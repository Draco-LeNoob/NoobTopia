package fr.noobtopia.plugin.engine.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
    private static final World world = Bukkit.getWorld("world");
    public static Location location;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location highest = world.getHighestBlockAt(8, 8).getLocation();
        location = new Location(world, highest.getX(), highest.getY() + 1, highest.getZ());

        if(sender instanceof Player player) {
            player.setFlying(false);
            player.teleport(CommandSpawn.location);
        }

        return true;
    }
}