package fr.noobtopia.plugin.features.mob.commands;

import fr.noobtopia.plugin.features.mob.MobReplacer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandSummonBoss implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args == null || args.length == 0) return false;

            EntityType type;

            try{
                type = EntityType.valueOf(args[0].toUpperCase());
            }catch(Exception e){ return false; }

            Entity entity = player.getWorld().spawnEntity(player.getLocation(), type);
            MobReplacer.replace(entity, true);
        }

        return true;
    }

    public static class Completer implements TabCompleter{

        @Nullable
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            return List.of(EntityType.values().toString());
        }
    }
}