package fr.noobtopia.plugin.mob.mobs;

import fr.noobtopia.plugin.mob.CustomEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import static fr.noobtopia.plugin.utils.ItemBuilder.basic;
import static org.bukkit.Material.*;

public class CustomZombie extends CustomEntity{
    private static final ItemStack[] LEATHER_SET = {
            basic(LEATHER_HELMET),
            basic(LEATHER_CHESTPLATE),
            basic(LEATHER_LEGGINGS),
            basic(LEATHER_BOOTS),
            basic(WOODEN_SWORD),
    }; private static final ItemStack[] IRON_SET = {
            basic(IRON_HELMET),
            basic(IRON_CHESTPLATE),
            basic(IRON_LEGGINGS),
            basic(IRON_BOOTS),
            basic(IRON_SWORD),
    }; private static final ItemStack[] DIAMOND_SET = {
            basic(DIAMOND_HELMET),
            basic(DIAMOND_CHESTPLATE),
            basic(DIAMOND_LEGGINGS),
            basic(DIAMOND_BOOTS),
            basic(DIAMOND_SWORD),
    }; private static final ItemStack[] NETHERITE_SET = {
            basic(NETHERITE_HELMET),
            basic(NETHERITE_CHESTPLATE),
            basic(NETHERITE_LEGGINGS),
            basic(NETHERITE_BOOTS),
            basic(NETHERITE_SWORD),
    };

    public CustomZombie(Zombie entity, boolean boss){
        super(entity, boss);

        if(level > 3)
            entity.setAdult();
        else
            entity.setBaby();

        int maxHealth = 5 * level;

        entity.setCanPickupItems(false);
        entity.setCustomName((boss ? "§9" : "§2") + "Zombie n. §e" + this.level);
        entity.setCustomNameVisible(true);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.setHealth(maxHealth);

        super.levelSets.put(1, LEATHER_SET);
        super.levelSets.put(2, IRON_SET);
        super.levelSets.put(3, DIAMOND_SET);
        super.levelSets.put(4, NETHERITE_SET);

        super.setStuff(false);
    }
}