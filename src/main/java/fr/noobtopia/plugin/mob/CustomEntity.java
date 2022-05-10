package fr.noobtopia.plugin.mob;

import fr.noobtopia.plugin.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static fr.noobtopia.plugin.NoobPlugin.random;

public class CustomEntity {
    protected HashMap<Integer, ItemStack[]> levelSets = new HashMap<>();

    protected LivingEntity entity;
    protected int level;

    public CustomEntity(LivingEntity entity, boolean boss) {
        this.entity = entity;

        if(boss)
            this.level = (int) Math.round(getLevelFrom(entity) * 1.1) + 3;
        else
            this.level = getLevelFrom(entity);

        if(this.level == 0) entity.remove();

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
        if(level < 3) return;

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
        inventory.clear();

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
        }

        while(itemInHand && mainHand == null){
            int random = random(0, items.length - 1);

            ItemStack item = items[random];
            String string = item.getType().toString().toLowerCase();

            if(string.endsWith("_sword")) mainHand = item;
            else if(string.endsWith("bow")) mainHand = item;
        }

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

    public static int getLevelFrom(Entity entity){ return getLevelFrom(entity.getLocation()); }
    public static int getLevelFrom(Location location){
        int x = Math.abs(location.getChunk().getX());
        int y = Math.abs(location.getChunk().getZ());

        int level = (int) Math.round(Math.sqrt(x + y));

        return level;
    }

    public static boolean isBoss() { return (int)(Math.random() * (1001)) == 1; }
}