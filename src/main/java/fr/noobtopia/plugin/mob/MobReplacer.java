package fr.noobtopia.plugin.mob;

import fr.noobtopia.plugin.mob.mobs.CustomSkeleton;
import fr.noobtopia.plugin.mob.mobs.CustomSpider;
import fr.noobtopia.plugin.mob.mobs.CustomZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobReplacer implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        boolean boss = CustomEntity.isBoss();

        switch(entity.getType()){
            case ZOMBIE: new CustomZombie((Zombie)entity, boss); break;
            case SKELETON: new CustomSkeleton((Skeleton)entity, boss); break;
            case SPIDER: new CustomSpider((Spider) entity, boss); break;
        }
    }
}