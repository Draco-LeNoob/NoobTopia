package fr.noobtopia.plugin.features.mob;

import fr.noobtopia.plugin.features.mob.mobs.CustomZombie;
import fr.noobtopia.plugin.features.mob.mobs.CustomCreeper;
import fr.noobtopia.plugin.features.mob.mobs.CustomSkeleton;
import fr.noobtopia.plugin.features.mob.mobs.CustomSpider;
import org.bukkit.entity.*;
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
            case CREEPER: new CustomCreeper((Creeper) entity, boss); break;
        }
    }
}