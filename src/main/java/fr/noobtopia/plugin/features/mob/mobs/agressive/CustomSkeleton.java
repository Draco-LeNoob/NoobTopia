package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import static fr.noobtopia.plugin.utils.ItemBuilder.basic;
import static org.bukkit.Material.*;

public class CustomSkeleton extends CustomEntity {
    public CustomSkeleton(LivingEntity entity, boolean boss) {
        super(entity, boss);

        int maxHealth = 4 * level;

        entity.setCustomName((boss ? "§9" : "§f") + "Squelette n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.setHealth(maxHealth);

        super.levelSets.put(1, LEATHER_SET);
        super.levelSets.put(2, IRON_SET);
        super.levelSets.put(3, DIAMOND_SET);
        super.levelSets.put(4, NETHERITE_SET);

        super.setStuff(true);
    }

    public static class LootListener implements Listener {
        @EventHandler
        public void onEntityKilled(EntityDeathEvent event){
            if(event.getEntityType() != EntityType.SKELETON) return;

            try{
                for(ItemStack item : event.getDrops()){
                    if(!CustomEntity.isEquipment(item)) event.getDrops().remove(item);
                }
            }catch(Exception e){}

            LivingEntity entity = event.getEntity();

            int boneOffset = 0;
            int arrowOffset = 0;

            int level = CustomEntity.getLevelOf(entity);

            while(level > 0){
                if(level % 3 == 0) boneOffset++;
                if(level % 4 == 0) arrowOffset++;

                level--;
            }

            for(int i = 0; i < boneOffset; i++) event.getDrops().add(ItemBuilder.basic(BONE));
            for(int i = 0; i < arrowOffset; i++) event.getDrops().add(ItemBuilder.basic(ARROW));
        }
    }

    private static final ItemStack[] LEATHER_SET = {
            basic(LEATHER_HELMET),
            basic(LEATHER_CHESTPLATE),
            basic(LEATHER_LEGGINGS),
            basic(LEATHER_BOOTS),
            basic(STONE_SWORD),
            basic(BOW),
    }; private static final ItemStack[] IRON_SET = {
            basic(IRON_HELMET),
            basic(IRON_CHESTPLATE),
            basic(IRON_LEGGINGS),
            basic(IRON_BOOTS),
            basic(IRON_SWORD),
            basic(BOW),
    }; private static final ItemStack[] DIAMOND_SET = {
            basic(DIAMOND_HELMET),
            basic(DIAMOND_CHESTPLATE),
            basic(DIAMOND_LEGGINGS),
            basic(DIAMOND_BOOTS),
            basic(DIAMOND_SWORD),
            new ItemBuilder(BOW).enchant(Enchantment.ARROW_DAMAGE, 3).build(),
    }; private static final ItemStack[] NETHERITE_SET = {
            basic(NETHERITE_HELMET),
            basic(NETHERITE_CHESTPLATE),
            basic(NETHERITE_LEGGINGS),
            basic(NETHERITE_BOOTS),
            basic(NETHERITE_SWORD),
            new ItemBuilder(BOW).enchant(Enchantment.ARROW_DAMAGE, 3).enchant(Enchantment.ARROW_FIRE, 1).build(),
    };
}