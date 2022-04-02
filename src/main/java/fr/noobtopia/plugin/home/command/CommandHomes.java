package fr.noobtopia.plugin.home.command;

import fr.noobtopia.plugin.home.Home;
import fr.noobtopia.plugin.utils.Folders;
import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandHomes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            Home[] homes = Home.getAllHomesOf(player);
            String homeColor = "§c";

            if(homes.length == 0){
                player.sendMessage(Messages.get("home.zero"));
            }else if(homes.length == 1){
                player.sendMessage("§6Vous n'avez qu'un home: " + homeColor + homes[0].getName());
            }else{
                StringBuilder message = new StringBuilder("§6Tous vos homes: ");

                for(int i = 0; i < homes.length; i++){
                    if(i != 0) message.append("§6, ");

                    message.append(homeColor).append(homes[i].getName());
                }

                player.sendMessage(message.toString());
            }

            return true;
        }else{
            sender.sendMessage(Messages.get("sender.console"));
        }

        return false;
    }
}