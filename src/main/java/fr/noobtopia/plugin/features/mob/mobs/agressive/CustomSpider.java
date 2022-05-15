package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Material.*;

public class CustomSpider extends CustomEntity {
    public CustomSpider(Spider entity, boolean boss){
        super(entity, boss);

        int maxHealth = 3 * level;

        entity.setCanPickupItems(false);
        entity.setCustomName((boss ? "§9" : "§8") + "Araignée n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(level / 2);
        entity.setHealth(maxHealth);
    }

    public static class AttackListener implements Listener {
        @EventHandler
        public void onSpiderHit(EntityDamageByEntityEvent event){
            Entity entity = event.getDamager();

            if(entity.getType() != EntityType.SPIDER) return;

            String[] split = entity.getName().split(" ");
            int level = Integer.parseInt(split[split.length - 1].substring(2));

            if(level < 10) return;
            if(!(event.getEntity() instanceof LivingEntity)) return;

            LivingEntity target = (LivingEntity) event.getEntity();

            int duration = level;
            while(duration > 3) duration -= 3;

            duration *= 40;

            if(level > 9 && level < 13){
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, 0));
            }else if(level > 12 && level < 16){
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, 1));
            }else if(level > 15){
                target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration, 0));
            }
        }
    }

    public static class LootListener implements Listener{
        @EventHandler
        public void onEntityKilled(EntityDeathEvent event){
            if(event.getEntityType() != EntityType.SPIDER) return;
            event.getDrops().clear();

            Entity entity = event.getEntity();

            int stringOffset = 0;
            int eyeOffset = 0;
            int fermentedEyeOffset = 0;

            int level = CustomEntity.getLevelOf(entity);

            while(level > 0){
                if(level % 4 == 0) stringOffset++;
                if(level % 6 == 0) eyeOffset++;
                if(level % 9 == 0) fermentedEyeOffset++;

                level--;
            }

            for(int i = 0; i < stringOffset; i++) event.getDrops().add(ItemBuilder.basic(STRING));
            for(int i = 0; i < eyeOffset; i++) event.getDrops().add(ItemBuilder.basic(SPIDER_EYE));
            for(int i = 0; i < fermentedEyeOffset; i++) event.getDrops().add(ItemBuilder.basic(FERMENTED_SPIDER_EYE));
        }
    }
}