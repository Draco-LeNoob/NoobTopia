package fr.noobtopia.plugin.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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

    public ItemBuilder lore(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();

        if(lore == null) lore = new ArrayList<>();
        lore.add(line);

        meta.setLore(lore);
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