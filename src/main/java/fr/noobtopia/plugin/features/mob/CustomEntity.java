package fr.noobtopia.plugin.features.mob;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.engine.initer.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static fr.noobtopia.plugin.NoobPlugin.random;

public class CustomEntity {
    protected HashMap<Integer, ItemStack[]> levelSets = new HashMap<>();

    protected LivingEntity entity;
    protected int level;

    public CustomEntity(LivingEntity entity, boolean boss) {
        this.entity = entity;

        if(boss)
            level = (int) Math.round(getLevelFrom(entity) * 1.1) + 3;
        else
            level = getLevelFrom(entity);

        if(level == 0) entity.remove();

        if(boss) {
            entity.getWorld().strikeLightningEffect(entity.getLocation());

            Bukkit.broadcastMessage(Messages.get("boss.spawn")
                    .replace("<<type>>", "§4" + entity.getType())
                    .replace("<<level>>", "§e" + this.level)
                    .replace("<<world>>", "§6" + entity.getWorld().getEnvironment().name())
                    .replace("<<location>>",
                            "§2" +
                                    (int) entity.getLocation().getX() + " " +
                                    (int) entity.getLocation().getY() + " " +
                                    (int) entity.getLocation().getZ()
                    )
            );
        }
    }

    protected void setStuff(boolean itemInHand){
        if(this.entity != null && this.entity.getEquipment() != null) this.entity.getEquipment().clear();

        if(level < 4) return;

        int offset = level;
        int setLevel = 0;

        while(offset > 3){
            offset -= 3;
            setLevel += 1;
        }

        randomStuff(offset, itemInHand, levelSets.get(setLevel));
    }

    private void randomStuff(int limit, boolean itemInHand, ItemStack... items){
        EntityEquipment inventory = entity.getEquipment();
        if(items == null) return;

        ItemStack helmet = null;
        ItemStack chest = null;
        ItemStack leggings = null;
        ItemStack boots = null;
        ItemStack mainHand = null;

        for(int i = 0; i < limit; i++){
            int random = random(0, items.length - 1);

            ItemStack item = items[random];
            String string = item.getType().toString().toLowerCase();

            if(string.endsWith("_helmet")) helmet = item;
            else if(string.endsWith("_chestplate")) chest = item;
            else if(string.endsWith("_leggings")) leggings = item;
            else if(string.endsWith("_boots")) boots = item;
            else if(string.endsWith("_sword")) mainHand = item;
            else if(string.endsWith("bow")) mainHand = item;
            else if(string.endsWith("trident")) mainHand = item;
        }

        while(itemInHand && mainHand == null){
            int random = random(0, items.length - 1);

            ItemStack item = items[random];
            String string = item.getType().toString().toLowerCase();

            if(string.endsWith("_sword")) mainHand = item;
            else if(string.endsWith("bow")) mainHand = item;
            else if(string.endsWith("trident")) mainHand = item;
        }

        assert(inventory != null);

        if(helmet != null) inventory.setHelmet(helmet);
        if(chest != null) inventory.setChestplate(chest);
        if(leggings != null) inventory.setLeggings(leggings);
        if(boots != null) inventory.setBoots(boots);
        if(mainHand != null) inventory.setItemInMainHand(mainHand);

        inventory.setHelmetDropChance(1);
        inventory.setChestplateDropChance(1);
        inventory.setLeggingsDropChance(1);
        inventory.setBootsDropChance(1);
        inventory.setItemInMainHandDropChance(1);
    }

    public static boolean isEquipment(ItemStack item){
        String string = item.getType().toString().toLowerCase();

        return string.endsWith("helmet")
        || string.endsWith("chestplate")
        || string.endsWith("leggings")
        || string.endsWith("boots")
        || string.endsWith("sword")
        || string.endsWith("bow")
        || string.endsWith("trident")
        || string.endsWith("crossbow");
    }

    public static int getLevelOf(Entity entity){
        if(!CustomEntity.isCustom(entity)) return -1;
        String[] split = entity.getCustomName().split(" ");
        String level = split[split.length - 1].substring(2);
        return Integer.parseInt(level);
    }
    public static int getLevelFrom(Entity entity){ return getLevelFrom(entity.getLocation()); }
    public static int getLevelFrom(Location location){
        int x = Math.abs(location.getChunk().getX());
        int y = Math.abs(location.getChunk().getZ());

        int level = (int) Math.round(Math.sqrt(x + y)) + (location.getWorld().getEnvironment() == World.Environment.NORMAL ? 0 : 10);

        return level;
    }

    public static boolean isBoss() { return (int)(Math.random() * (1001)) == 1; }
    public static boolean isBoss(Entity entity) { return entity.getCustomName().startsWith("§9"); }
    public static boolean isCustom(Entity entity){ return entity.isCustomNameVisible() && entity.getCustomName().startsWith("§"); }
}