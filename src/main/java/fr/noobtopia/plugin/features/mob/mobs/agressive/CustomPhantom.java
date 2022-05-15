package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.bukkit.Material.PHANTOM_MEMBRANE;

public class CustomPhantom extends CustomEntity {
    public CustomPhantom(Phantom entity, boolean boss) {
        super(entity, boss);

        int maxHealth = level;

        entity.setCustomName((boss ? "§9" : "§2") + "Phantom n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(level);
        entity.setHealth(maxHealth);
    }

    public static class LootListener implements Listener {
        @EventHandler
        public void onEntityKilled(EntityDeathEvent event){
            if(event.getEntityType() != EntityType.PHANTOM) return;
            event.getDrops().clear();

            Entity entity = event.getEntity();

            int membraneOffset = 0;
            int level = CustomEntity.getLevelOf(entity);

            while(level > 0){
                if(level % 6 == 0) membraneOffset++;

                level--;
            }

            for(int i = 0; i < membraneOffset; i++) event.getDrops().add(ItemBuilder.basic(PHANTOM_MEMBRANE));
        }
    }
}