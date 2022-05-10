package fr.noobtopia.plugin.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(Material material){ this.item = new ItemStack(material); }
    public ItemStack build(){ return item; }

    public ItemBuilder displayName(String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder amount(int amount){
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder lore(String... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(List.of(lore));

        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable){
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(unbreakable);

        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level){
        item.addEnchantment(enchantment, level);
        return this;
    }

    public static ItemStack basic(Material material){ return new ItemStack(material); }
    public static ItemStack randomBasic(Material... materials){
        if(materials == null || materials.length == 0) return null;

        Material material = materials[0 + (int)(Math.random() * ((materials.length - 1 - 0) + 1))];
        return new ItemStack(material);
    }
}