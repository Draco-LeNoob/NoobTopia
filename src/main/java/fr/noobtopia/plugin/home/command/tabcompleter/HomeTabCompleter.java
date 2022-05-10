package fr.noobtopia.plugin.home.command.tabcompleter;

import fr.noobtopia.plugin.home.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeTabCompleter implements TabCompleter {
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