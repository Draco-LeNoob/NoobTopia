package fr.noobtopia.plugin.economy.command;

import fr.noobtopia.plugin.economy.Economy;
import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 2){
                Player target = null;
                double amount = 0;

                try{
                    target = Bukkit.getPlayer(args[0]);
                    amount = Double.parseDouble(args[1]);
                }catch(Exception e){
                    player.sendMessage(Messages.get("economy.pay.help"));
                }

                if(target != null && amount != 0){
                    if(amount > 0){
                        if(Economy.getBalanceOf(player).getBalance() >= amount){
                            Economy.pay(player, target, amount);
                            player.sendMessage(Messages.get("economy.pay"));
                        }else{
                            player.sendMessage(Messages.get("economy.pay.poor"));
                        }
                    }else{
                        player.sendMessage(Messages.get("economy.pay.negative"));
                    }
                }
            }else{
                player.sendMessage(Messages.get("economy.pay.help"));
            }

            return true;
        }else{
            sender.sendMessage(Messages.get("sender.console"));
        }

        return false;
    }
}