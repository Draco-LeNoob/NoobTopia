package fr.noobtopia.plugin.features.mob.mobs;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
}