package fr.noobtopia.plugin.features.home.command;

import fr.noobtopia.plugin.features.home.Home;
import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 1){
                String homeName = args[0];

                if(Home.exists(player, homeName)){
                    Home home = Home.load(player, homeName);

                    if (home != null) {
                        player.teleport(home.getLocation());
                        player.sendMessage(Messages.get("home.use"));
                    }else{
                        player.sendMessage(Messages.get("home.missing"));
                    }
                }else{
                    player.sendMessage(Messages.get("home.missing"));
                }
            }else{
                player.sendMessage(Messages.get("home.missing_name"));
            }

            return true;
        }else{
            sender.sendMessage(Messages.get("sender.console"));
        }

        return false;
    }

    public static class Completer implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player player){
                if(args.length == 1){
                    List<String> homes = new ArrayList<String>();

                    for(Home home : Home.getAllHomesOf(player)){
                        homes.add(home.getName());
                    }

                    return homes;
                }
            }

            return null;
        }
    }
}