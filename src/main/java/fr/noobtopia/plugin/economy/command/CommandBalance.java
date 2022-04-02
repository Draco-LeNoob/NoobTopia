package fr.noobtopia.plugin.economy.command;

import fr.noobtopia.plugin.economy.Balance;
import fr.noobtopia.plugin.economy.Economy;
import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBalance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(Economy.needBalance(player)) Economy.registerBalance(player);

            Balance balance = Economy.getBalanceOf(player);

            player.sendMessage(Messages.get("economy.balance").replaceAll("<<balance>>", "" + balance.getBalance()));
            return true;
        }else{
            sender.sendMessage(Messages.get("sender.console"));
        }

        return false;
    }
}