package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.features.economy.InitBalances;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners.ArrowHitListener;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners.ArrowShotListener;
import fr.noobtopia.plugin.engine.listeners.RespawnListener;
import fr.noobtopia.plugin.look.LookJoinListener;
import fr.noobtopia.plugin.look.LookLeaveListener;
import fr.noobtopia.plugin.features.mob.mana.listeners.BossKilledEvent;
import fr.noobtopia.plugin.features.mob.mana.listeners.ManaUiClickListener;
import fr.noobtopia.plugin.features.mob.DifficultyChangeDisplay;
import fr.noobtopia.plugin.features.mob.MobReplacer;
import fr.noobtopia.plugin.features.mob.mobs.CustomCreeper;
import fr.noobtopia.plugin.features.mob.mobs.CustomSpider;
import fr.noobtopia.plugin.features.permission.PermissionJoinListener;
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

        register(new ManaUiClickListener());
        register(new BossKilledEvent());

        register(new CustomSpider.AttackListener());
        register(new CustomCreeper.ExplodeListener());
    }

    private static void register(Listener listener){
        NoobPlugin.instance.getServer().getPluginManager().registerEvents(listener, NoobPlugin.instance);
    }
}