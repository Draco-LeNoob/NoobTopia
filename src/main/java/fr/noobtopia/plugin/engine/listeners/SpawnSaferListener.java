package fr.noobtopia.plugin.engine.listeners;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpawnSaferListener implements Listener {
    @EventHandler public void onPlayerDamage(EntityDamageEvent event){
        if(event.getEntity().getWorld().getEnvironment() != World.Environment.NORMAL) return;

        event.setCancelled(CustomEntity.getLevelFrom(event.getEntity()) == 0 && event.getEntityType() == EntityType.PLAYER);
    }

    @EventHandler public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().getWorld().getEnvironment() != World.Environment.NORMAL) return;
        if(event.getPlayer() == null) event.setCancelled(true);
        event.setCancelled(CustomEntity.getLevelFrom(event.getBlockPlaced().getLocation()) == 0 && !event.getPlayer().isOp() && event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event){
        if(event.getPlayer().getWorld().getEnvironment() != World.Environment.NORMAL) return;
        if(event.getPlayer() == null) event.setCancelled(true);

        event.setCancelled(CustomEntity.getLevelFrom(event.getBlock().getLocation()) == 0 && !event.getPlayer().isOp() && event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}