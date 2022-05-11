package fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.EnchantmentExplosiveBow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ArrowShotListener implements Listener {
    @EventHandler
    public void onArrowShot(EntityShootBowEvent event){
        ItemStack bow = event.getBow();
        Entity projectile = event.getProjectile();

        if(projectile.getType() != EntityType.ARROW) return;

        int level = bow.getItemMeta().getEnchantLevel(EnchantmentExplosiveBow.instance);
        projectile.setMetadata("explosive", new FixedMetadataValue(NoobPlugin.instance, level));
    }
}