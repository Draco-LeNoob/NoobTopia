package fr.noobtopia.plugin.enchantment;

import fr.noobtopia.plugin.enchantment.enchantments.explosive.EnchantmentExplosiveBow;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchantments {
    public static final Enchantment[] enchantments = {
            EnchantmentExplosiveBow.instance
    };

    public static void register(){
        for(Enchantment enchantment : enchantments){
            boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);

            if(!registered) register(enchantment);
        }
    }

    private static void register(Enchantment enchantment){
        boolean registered = true;

        try{
            Field field = Enchantment.class.getDeclaredField("acceptingNew");

            field.setAccessible(true);
            field.set(null, true);

            Enchantment.registerEnchantment(enchantment);
        }catch(Exception e){
            registered = false;
            e.printStackTrace();
        }

        if(registered) System.out.println("Enchantment " + enchantment.getName() + " has been registered successfully !");
    }
}