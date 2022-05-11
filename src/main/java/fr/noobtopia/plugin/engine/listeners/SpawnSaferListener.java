package fr.noobtopia.plugin.engine.listeners;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpawnSaferListener implements Listener {
    @EventHandler public void onPlayerDamage(EntityDamageEvent event){
        event.setCancelled(CustomEntity.getLevelFrom(event.getEntity()) == 0 && event.getEntityType() == EntityType.PLAYER);
    }

    @EventHandler public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer() == null) event.setCancelled(true);
        event.setCancelled(CustomEntity.getLevelFrom(event.getBlockPlaced().getLocation()) == 0 && !event.getPlayer().isOp());
    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event){
        if(event.getPlayer() == null) event.setCancelled(true);
        event.setCancelled(CustomEntity.getLevelFrom(event.getBlock().getLocation()) == 0 && !event.getPlayer().isOp());
    }
}