package fr.noobtopia.plugin.features.home.command;

import fr.noobtopia.plugin.features.home.Home;
import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 1){
                String homeName = args[0];

                if(Home.exists(player, homeName)) Home.getHomeFile(player, homeName).delete();

                player.sendMessage(Messages.get("home.del"));
            }else{
                player.sendMessage(Messages.get("home.missing_name"));
            }

            return true;
        }else{
            sender.sendMessage(Messages.get("sender.console"));
        }

        return false;
    }
}