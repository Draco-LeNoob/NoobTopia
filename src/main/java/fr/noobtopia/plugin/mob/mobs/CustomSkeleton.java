package fr.noobtopia.plugin.mob.mobs;

import fr.noobtopia.plugin.mob.CustomEntity;
import fr.noobtopia.plugin.utils.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import static fr.noobtopia.plugin.utils.ItemBuilder.basic;
import static org.bukkit.Material.*;

public class CustomSkeleton extends CustomEntity {
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

    public CustomSkeleton(LivingEntity entity, boolean boss) {
        super(entity, boss);

        int maxHealth = 4 * level;

        entity.setCanPickupItems(false);
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
}