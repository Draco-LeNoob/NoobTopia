package fr.noobtopia.plugin.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.economy.InitBalances;
import fr.noobtopia.plugin.enchantment.enchantments.explosive.listeners.ArrowHitListener;
import fr.noobtopia.plugin.enchantment.enchantments.explosive.listeners.ArrowShotListener;
import fr.noobtopia.plugin.listeners.RespawnListener;
import fr.noobtopia.plugin.look.LookJoinListener;
import fr.noobtopia.plugin.look.LookLeaveListener;
import fr.noobtopia.plugin.mob.DifficultyChangeDisplay;
import fr.noobtopia.plugin.mob.MobReplacer;
import fr.noobtopia.plugin.mob.mobs.CustomSpider;
import fr.noobtopia.plugin.permission.PermissionJoinListener;
import org.bukkit.event.Listener;

public class Listeners {
    public static void init(){
        register(new InitBalances());

        register(new LookJoinListener());
        register(new LookLeaveListener());

        register(new PermissionJoinListener());

        register(new MobReplacer());
        register(new DifficultyChangeDisplay());

        register(new ArrowShotListener());
        register(new ArrowHitListener());

        register(new RespawnListener());

        register(new CustomSpider.AttackListener());
    }

    private static void register(Listener listener){
        NoobPlugin.instance.getServer().getPluginManager().registerEvents(listener, NoobPlugin.instance);
    }
}