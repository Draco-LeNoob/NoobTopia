package fr.noobtopia.plugin.features.mob.mobs;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CustomCreeper extends CustomEntity {
    public CustomCreeper(Creeper entity, boolean boss){
        super(entity, boss);

        int maxHealth = 2 * level;

        entity.setCustomName((boss ? "§9" : "§2") + "Creeper n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.setHealth(maxHealth);
    }

    public static class ExplodeListener implements Listener {
        @EventHandler
        public void onCreeperExplode(EntityExplodeEvent event){
            Entity entity = event.getEntity();

            if(!(entity instanceof Creeper creeper)) return;

            event.setCancelled(true);
            creeper.getWorld().createExplosion(creeper.getLocation(), CustomEntity.getLevelOf(creeper) / 2);
            try{ creeper.eject(); } catch(Exception e){}
        }
    }
}