package fr.noobtopia.plugin.enchantment.listeners;

import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowHitListener implements Listener {
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event){
        Projectile arrow = event.getEntity();

        if(!arrow.hasMetadata("explosive")) return;

        arrow.getMetadata("explosive").forEach(metadata -> arrow.getWorld().createExplosion(arrow.getLocation(), metadata.asInt()));
        arrow.remove();
    }
}