package fr.noobtopia.plugin.features.mana.listeners;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.engine.initer.Messages;
import fr.noobtopia.plugin.features.mana.Mana;
import fr.noobtopia.plugin.features.mob.CustomEntity;
import fr.noobtopia.plugin.features.mana.ManaType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BossKilledEvent implements Listener {
    @EventHandler
    public void onBossKilled(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof LivingEntity entity)) return;
        if(!CustomEntity.isCustom(entity)) return;
        if(entity.getHealth() - event.getDamage() > 0) return;
        if(!(event.getDamager() instanceof Player player)) return;
        if(!CustomEntity.isBoss(entity)) return;

        int level = CustomEntity.getLevelOf(entity);
        if(level < 1) return;

        ManaType[] types = ManaType.fromEntity(entity);

        int amount = NoobPlugin.random(2 * level, 4 * level);
        for(ManaType type : types) Mana.add(player, type, amount);

        StringBuilder manas = new StringBuilder();

        for(int i = 0; i < types.length; i++) manas.append(types[i].getDisplay() + "§9" + (i != types.length - 1 ? ", " : ""));

        player.sendMessage(Messages.get("boss.killed")
                .replace("<<name>>", entity.getCustomName())
                .replace("<<amount>>", "§e" + amount)
                .replace("<<types>>", manas)
        );
    }
}