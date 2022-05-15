package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.engine.listeners.PlayerDeathListener;
import fr.noobtopia.plugin.engine.listeners.PlayerRespawnListener;
import fr.noobtopia.plugin.engine.listeners.SpawnSaferListener;
import fr.noobtopia.plugin.features.economy.InitBalances;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners.ArrowHitListener;
import fr.noobtopia.plugin.engine.enchantment.enchantments.explosive.listeners.ArrowShotListener;
import fr.noobtopia.plugin.features.mob.listeners.ExperienceGiveListener;
import fr.noobtopia.plugin.features.mob.mobs.agressive.*;
import fr.noobtopia.plugin.look.LookJoinListener;
import fr.noobtopia.plugin.look.LookLeaveListener;
import fr.noobtopia.plugin.features.mana.listeners.BossKilledEvent;
import fr.noobtopia.plugin.features.mana.listeners.ManaUiClickListener;
import fr.noobtopia.plugin.features.mob.DifficultyChangeDisplay;
import fr.noobtopia.plugin.features.mob.MobReplacer;
import fr.noobtopia.plugin.features.permission.PermissionJoinListener;
import org.bukkit.event.Listener;

public class Listeners {
    public static void init(){
        registerAll(
                new InitBalances(),

                new LookJoinListener(),
                new LookLeaveListener(),

                new PermissionJoinListener(),

                new MobReplacer(),
                new DifficultyChangeDisplay(),

                new ArrowShotListener(),
                new ArrowHitListener(),

                new SpawnSaferListener(),

                new ManaUiClickListener(),
                new BossKilledEvent(),
                new ExperienceGiveListener(),
                new PlayerRespawnListener(),
                new PlayerDeathListener(),

                new CustomCreeper.ExplodeListener(), new CustomCreeper.LootListener(),
                new CustomDrowned.LootListener(),
                new CustomPhantom.LootListener(),
                new CustomSkeleton.LootListener(),
                new CustomSpider.AttackListener(), new CustomSpider.LootListener(),
                new CustomWolf.TameCanceler(),
                new CustomZombie.LootListener()
        );
    }

    private static void registerAll(Listener... listeners){ for(Listener listener : listeners) register(listener); }
    private static void register(Listener listener){ NoobPlugin.instance.getServer().getPluginManager().registerEvents(listener, NoobPlugin.instance); }
}