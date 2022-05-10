package fr.noobtopia.plugin.enchantment.enchantments.explosive;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantmentExplosiveBow extends Enchantment {
    public static EnchantmentExplosiveBow instance;

    private static final String name = "explosive_bow";
    private static final int maxLevel = 300;
    private static final int minLevel = 1;
    private static final EnchantmentTarget target = EnchantmentTarget.BOW;
    private static final boolean treasure = true;
    private static final boolean curse = false;
    private static final Enchantment[] conflicts = { Enchantment.ARROW_INFINITE };

    static{ instance = new EnchantmentExplosiveBow(); }
    private EnchantmentExplosiveBow(){ super(NamespacedKey.minecraft(name)); }

    @Override public String getName() { return name; }
    @Override public int getMaxLevel() { return maxLevel; }
    @Override public int getStartLevel() { return minLevel; }
    @Override public EnchantmentTarget getItemTarget() { return target; }
    @Override public boolean isTreasure() { return treasure; }
    @Override public boolean isCursed() { return curse; }
    @Override public boolean conflictsWith(Enchantment enchantment) { return List.of(conflicts).contains(enchantment); }
    @Override public boolean canEnchantItem(ItemStack itemStack) { return true; }
}