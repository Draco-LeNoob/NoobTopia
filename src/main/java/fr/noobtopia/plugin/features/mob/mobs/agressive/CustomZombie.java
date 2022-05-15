package fr.noobtopia.plugin.features.mob.mobs.agressive;

import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static fr.noobtopia.plugin.utils.ItemBuilder.basic;
import static org.bukkit.Material.*;
import static org.bukkit.enchantments.Enchantment.*;

public class CustomZombie extends CustomEntity{
    public CustomZombie(Zombie entity, boolean boss){
        super(entity, boss);

        if(level > 3){
            entity.setAdult();
        }else{
            entity.setBaby();
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 2, false, false, false));
        }

        int maxHealth = 5 * level;

        entity.setCanPickupItems(false);
        entity.setCustomName((boss ? "§9" : "§2") + "Zombie n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(level);
        entity.setHealth(maxHealth);

        super.levelSets.put(1, LEATHER_SET);
        super.levelSets.put(2, LEATHER_SET_2);
        super.levelSets.put(3, CHAIN_MAIL_SET);
        super.levelSets.put(4, CHAIN_MAIL_SET_2);
        super.levelSets.put(5, IRON_SET);
        super.levelSets.put(6, IRON_SET_2);
        super.levelSets.put(7, DIAMOND_SET);
        super.levelSets.put(8, DIAMOND_SET_2);
        super.levelSets.put(9, NETHERITE_SET);
        super.levelSets.put(10, NETHERITE_SET_2);

        super.setStuff(false);
    }

    public static class LootListener implements Listener {
        @EventHandler
        public void onEntityKilled(EntityDeathEvent event){
            if(event.getEntityType() != EntityType.ZOMBIE) return;

            try{
                for(ItemStack item : event.getDrops()){
                    if(!CustomEntity.isEquipment(item)) event.getDrops().remove(item);
                }
            }catch(Exception e){}

            LivingEntity entity = event.getEntity();

            int rottenFleshOffset = 0;

            int level = CustomEntity.getLevelOf(entity);

            while(level > 0){
                if(level % 5 == 0) rottenFleshOffset++;

                level--;
            }

            for(int i = 0; i < rottenFleshOffset; i++) event.getDrops().add(ItemBuilder.basic(ROTTEN_FLESH));
        }
    }

    private static final ItemStack[] LEATHER_SET = {
            basic(LEATHER_HELMET),
            basic(LEATHER_CHESTPLATE),
            basic(LEATHER_LEGGINGS),
            basic(LEATHER_BOOTS),
            basic(WOODEN_SWORD),
    }; private static final ItemStack[] LEATHER_SET_2 = {
            new ItemBuilder(LEATHER_HELMET).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(LEATHER_CHESTPLATE).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(LEATHER_LEGGINGS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(LEATHER_BOOTS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(WOODEN_SWORD).enchant(DAMAGE_ALL, 2).build(),
    }; private static final ItemStack[] CHAIN_MAIL_SET = {
            basic(CHAINMAIL_HELMET),
            basic(CHAINMAIL_CHESTPLATE),
            basic(CHAINMAIL_LEGGINGS),
            basic(CHAINMAIL_BOOTS),
            basic(STONE_SWORD),
    }; private static final ItemStack[] CHAIN_MAIL_SET_2 = {
            new ItemBuilder(CHAINMAIL_HELMET).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(CHAINMAIL_CHESTPLATE).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(CHAINMAIL_LEGGINGS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(CHAINMAIL_BOOTS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(STONE_SWORD).enchant(DAMAGE_ALL, 2).build(),
    }; private static final ItemStack[] IRON_SET = {
            basic(IRON_HELMET),
            basic(IRON_CHESTPLATE),
            basic(IRON_LEGGINGS),
            basic(IRON_BOOTS),
            basic(IRON_SWORD),
    }; private static final ItemStack[] IRON_SET_2 = {
            new ItemBuilder(IRON_HELMET).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(IRON_CHESTPLATE).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(IRON_LEGGINGS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(IRON_BOOTS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(IRON_SWORD).enchant(DAMAGE_ALL, 2).build(),
    }; private static final ItemStack[] DIAMOND_SET = {
            basic(DIAMOND_HELMET),
            basic(DIAMOND_CHESTPLATE),
            basic(DIAMOND_LEGGINGS),
            basic(DIAMOND_BOOTS),
            basic(DIAMOND_SWORD),
    }; private static final ItemStack[] DIAMOND_SET_2 = {
            new ItemBuilder(DIAMOND_HELMET).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(DIAMOND_CHESTPLATE).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(DIAMOND_LEGGINGS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(DIAMOND_BOOTS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(DIAMOND_SWORD).enchant(DAMAGE_ALL, 2).build(),
    }; private static final ItemStack[] NETHERITE_SET = {
            basic(NETHERITE_HELMET),
            basic(NETHERITE_CHESTPLATE),
            basic(NETHERITE_LEGGINGS),
            basic(NETHERITE_BOOTS),
            basic(NETHERITE_SWORD),
    }; private static final ItemStack[] NETHERITE_SET_2 = {
            new ItemBuilder(NETHERITE_HELMET).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(NETHERITE_CHESTPLATE).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(NETHERITE_LEGGINGS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(NETHERITE_BOOTS).enchant(PROTECTION_ENVIRONMENTAL, 2).build(),
            new ItemBuilder(NETHERITE_SWORD).enchant(DAMAGE_ALL, 2).build(),
    };
}