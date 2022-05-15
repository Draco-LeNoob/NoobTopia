package fr.noobtopia.plugin.features.mob;

import fr.noobtopia.plugin.features.mob.mobs.agressive.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobReplacer implements Listener {
    @EventHandler public void onMobSpawn(EntitySpawnEvent event){ MobReplacer.replace(event.getEntity()); }

    public static void replace(Entity entity){ replace(entity, CustomEntity.isBoss()); }
    public static void replace(Entity entity, boolean boss){
        if(entity == null) return;

        switch(entity.getType()){
            case ZOMBIE: new CustomZombie((Zombie)entity, boss); break;
            case DROWNED: new CustomDrowned((Drowned) entity, boss); break;
            case SKELETON: new CustomSkeleton((Skeleton) entity, boss); break;
            case SPIDER: new CustomSpider((Spider) entity, boss); break;
            case CREEPER: new CustomCreeper((Creeper) entity, boss); break;
            case PHANTOM: new CustomPhantom((Phantom) entity, boss); break;
            case WOLF: new CustomWolf((Wolf) entity, boss); break;
        }
    }
}