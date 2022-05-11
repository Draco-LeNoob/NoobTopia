package fr.noobtopia.plugin.look;

import fr.noobtopia.plugin.engine.commands.CommandSpawn;
import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LookRespawnListener implements Listener {
    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event){
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();

        if(player.getHealth() - event.getDamage() > 0) return;
        event.setCancelled(true);


        player.teleport(CommandSpawn.location);
        player.setHealth(player.getHealthScale());
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.resetTitle();
        player.closeInventory();
        player.setFlying(false);

        String reason = switch(event.getCause()){
            case BLOCK_EXPLOSION -> "§4 il a explosé !";
            case CONTACT -> "§2 il a été piqué à mort...";
            case CRAMMING -> "§8 il a suffoqué avec d'autres entités";
            case DRAGON_BREATH -> "§d il a succombé au souffle du Dragon...";
            case DROWNING -> "§1 il a oublié qu'on ne pouvait pas respirer sous l'eau...";
            case DRYOUT -> "§1 il a oublié qu'il ne pouvait respirer que dans l'eau...";
            case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK -> "§c il a combattu jusqu'à sa mort...";
            case ENTITY_EXPLOSION -> "§4 a été explosé par un creeper !";
            case FALL -> "§a il a oublié la gravité et s'est lamentablement écrasé au sol...";
            case FALLING_BLOCK -> "§a il a été écrasé à mort";
            case FIRE, FIRE_TICK -> "§4 il a brûlé à mort";
            case FREEZE -> "§f il a été gelé à mort";
            case HOT_FLOOR -> "§c il a marché sur un sol un peu trop chaud : un sol en magma blocks...";
            case LAVA -> "§c il a essayé de nager dans la lave... mais c'est de la lave...";
            case LIGHTNING -> "§b il a littéralement eu un coup de foudre";
            case MAGIC -> "§5 il a un peu trop subit les effets d'une potion";
            case MELTING -> "§f il a subi la fonte d'un bonhomme ne neige (je n'ai pas de message mais je comprends pas dans quel cas cette mort arrive xD)";
            case POISON -> "§a il a été empoisonné... à mort...";
            case PROJECTILE -> "§7 il s'est trop fait tirer dessus";
            case STARVATION -> "§c il a oublié de manger... il est con lui !";
            case SUFFOCATION -> "§8 il a suffoqué dans un mur";
            case SUICIDE -> "§e il s'est suicidé...";
            case THORNS -> "§8 il a été piqué à mort par les épines sur l'armure de son adversaire";
            case VOID -> "§8 il est tombé dans le vide";
            case WITHER -> "§8 il a été empoisonné par le poison du wither...";
            default -> "§4ERROR : NO REASON FOUND";
        };

        Bukkit.broadcastMessage(Messages.get("player.death")
                .replace("<<killed>>", "§2" + player.getPlayerListName())
                .replace("<<reason>>", reason)
        );
    }
}