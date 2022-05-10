package fr.noobtopia.plugin.enchantment.enchantments.explosive.commands;

import fr.noobtopia.plugin.enchantment.enchantments.explosive.EnchantmentExplosiveBow;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGiveExplosive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            ItemStack bow = new ItemBuilder(Material.BOW).enchant(EnchantmentExplosiveBow.instance, Integer.parseInt(args[0])).build();
            player.getInventory().addItem(bow);
        }

        return true;
    }
}