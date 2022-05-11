package fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowHitListener implements Listener {
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        if(!(projectile instanceof Arrow)) return;

        Arrow arrow = (Arrow) projectile;

        int size = arrow.getMetadata("explosive").get(0).asInt();

        if(size <= 0) return;

        arrow.getWorld().createExplosion(arrow.getLocation(), size);
        arrow.remove();
    }
}