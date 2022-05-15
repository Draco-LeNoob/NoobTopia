package fr.noobtopia.plugin.features.mana.listeners;

import fr.noobtopia.plugin.features.mana.commands.CommandMana;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class ManaUiClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player player)) return;
        if(player == null || event.getWhoClicked().getOpenInventory() == null) return;

        InventoryView inventory = event.getWhoClicked().getOpenInventory();

        if(!inventory.getTitle().equals(CommandMana.getInventoryName(player))) return;

        event.setCancelled(true);
    }
}