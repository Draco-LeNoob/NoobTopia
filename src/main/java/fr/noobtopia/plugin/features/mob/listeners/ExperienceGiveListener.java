package fr.noobtopia.plugin.features.mob.listeners;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class ExperienceGiveListener implements Listener {
    @EventHandler
    public void onEntityKill(EntityDeathEvent event){
        if(event.getEntityType() == EntityType.PLAYER) return;

        int xp = CustomEntity.getLevelOf(event.getEntity());

        event.setDroppedExp(xp >= 0 ? xp : 0);
    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event){ event.setExpToDrop(0); }
}