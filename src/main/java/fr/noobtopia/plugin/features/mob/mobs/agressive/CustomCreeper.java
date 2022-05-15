package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import static org.bukkit.Material.GUNPOWDER;

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

    public static class LootListener implements Listener{
        @EventHandler
        public void onEntityKilled(EntityDeathEvent event){
            if(event.getEntityType() != EntityType.CREEPER) return;
            event.getDrops().clear();

            LivingEntity entity = event.getEntity();

            int gunPowderOffset = 0;

            int level = CustomEntity.getLevelOf(entity);

            while(level > 0){
                if(level % 4 == 0) gunPowderOffset++;

                level--;
            }

            for(int i = 0; i < gunPowderOffset; i++) event.getDrops().add(ItemBuilder.basic(GUNPOWDER));
        }
    }
}