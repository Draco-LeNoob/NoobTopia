package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomWolf extends CustomEntity {
    public CustomWolf(Wolf entity, boolean boss){
        super(entity, boss);

        int maxHealth = level;

        entity.setAdult();
        entity.setCustomName((boss ? "§9" : "§7") + "Loup n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(3 * level);
        entity.setHealth(maxHealth);

        new AttackTask(entity).runTaskTimer(NoobPlugin.instance, 0, 20);
    }

    public static class AttackTask extends BukkitRunnable {
        private int radius;
        private Wolf wolf;

        public AttackTask(Wolf wolf){
            this.wolf = wolf;
            this.radius = CustomEntity.getLevelOf(wolf);
        }

        @Override
        public void run() {
            if(wolf == null || wolf.getTarget() != null) return;
            if(wolf.getTarget() != null && !wolf.getTarget().isDead()) return;

            for(Entity entity : wolf.getNearbyEntities(radius, radius, radius)){
                if(!(entity instanceof LivingEntity target)) continue;
                if(target.getType() == EntityType.WOLF) continue;
                if(target instanceof Player player && (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)) continue;

                wolf.setTarget(target);
            }
        }
    }

    public static class TameCanceler implements Listener {
        @EventHandler
        public void onWolfTamed(EntityTameEvent event){
            if(event.getEntityType() == EntityType.WOLF) event.setCancelled(true);
        }
    }
}