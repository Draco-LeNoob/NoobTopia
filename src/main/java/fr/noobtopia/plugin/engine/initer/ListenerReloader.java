package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.features.mob.MobReplacer;
import org.bukkit.Bukkit;

public class ListenerReloader {
    public static void register(){
        Bukkit.getWorlds().forEach(world -> world.getEntities().forEach(entity -> MobReplacer.replace(entity)));
    }
}