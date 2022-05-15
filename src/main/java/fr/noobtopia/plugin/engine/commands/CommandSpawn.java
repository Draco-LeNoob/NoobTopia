package fr.noobtopia.plugin.engine.commands;

import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
    private static final World world = Bukkit.getWorld("world");
    private static Location highest = world.getHighestBlockAt(8, 8).getLocation();
    public static Location location = new Location(world, highest.getX(), highest.getY() + 1, highest.getZ());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        location = new Location(world, highest.getX(), highest.getY() + 1, highest.getZ());

        if(sender instanceof Player player) {
            player.setFlying(false);
            player.teleport(CommandSpawn.location);
            player.sendMessage(Messages.get("spawn"));
        }

        return true;
    }
}