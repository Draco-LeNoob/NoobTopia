package fr.noobtopia.plugin.features.mana.commands;

import fr.noobtopia.plugin.features.mana.ManaType;
import fr.noobtopia.plugin.features.mana.Mana;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandMana implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            Inventory inventory = Bukkit.createInventory(null, 54, getInventoryName(player));

            inventory.setItem(19, new ItemBuilder(Material.FIRE_CHARGE).displayName("§4Mana du Feu")
                    .lore("§e" + Mana.get(player, ManaType.FIRE) + "§4 manas de Feu")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_FIRE) + "§4 manas de Feu maudits")
                    .build());
            inventory.setItem(22, new ItemBuilder(Material.WATER_BUCKET).displayName("§1Mana de l'Eau")
                    .lore("§e" + Mana.get(player, ManaType.WATER) + "§1 manas d'Eau")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_WATER) + "§1 manas d'Eau maudits")
                    .build());
            inventory.setItem(25, new ItemBuilder(Material.GRASS_BLOCK).displayName("§2Mana de la Terre")
                    .lore("§e" + Mana.get(player, ManaType.EARTH) + "§2 manas de Terre")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_EARTH) + "§2 manas de Terre maudits")
                    .build());
            inventory.setItem(37, new ItemBuilder(Material.CRAFTING_TABLE).displayName("§8Mana de l'Artisanat")
                    .lore("§e" + Mana.get(player, ManaType.ARTISAN) + "§8 manas d'Artisanat")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_ARTISAN) + "§8 manas d'Artisanat maudits")
                    .build());
            inventory.setItem(40, new ItemBuilder(Material.IRON_SWORD).displayName("§7Mana de la Guerre")
                    .lore("§e" + Mana.get(player, ManaType.WAR) + "§7 manas de Guerre")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_WAR) + "§7 manas de Guerre maudits")
                    .build());
            inventory.setItem(43, new ItemBuilder(Material.ARROW).displayName("§aMana du Vent")
                    .lore("§e" + Mana.get(player, ManaType.WIND) + "§a manas de Vent")
                    .lore("§e" + Mana.get(player, ManaType.CURSED_WIND) + "§a manas de Vent maudits")
                    .build());

            player.openInventory(inventory);
        }

        return true;
    }

    public static String getInventoryName(Player player){ return "§9Mana de " + player.getPlayerListName(); }
}