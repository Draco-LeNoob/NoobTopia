package fr.noobtopia.plugin.features.home;

import fr.noobtopia.plugin.engine.io.Folder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.*;

public class Home {
    private final Player owner;
    private final String name;
    private final Location location;

    public Home(Player owner, String name, Location location){
        this.owner = owner;
        this.name = name;
        this.location = location;
    }

    public static Home load(Player player, String name) {
        if(!Home.exists(player, name)) return null;

        Location location = null;

        try{
            FileReader read = new FileReader(Home.getHomeFile(player, name));
            BufferedReader reader = new BufferedReader(read);

            String line = reader.readLine();

            reader.close();
            read.close();

            String[] split = line.split(" ");

            double x = Double.parseDouble(split[0]);
            double y = Double.parseDouble(split[1]);
            double z = Double.parseDouble(split[2]);
            float pitch = Float.parseFloat(split[3]);
            float yaw = Float.parseFloat(split[4]);
            World world = Bukkit.getWorld(split[5]);

            location = new Location(world, x, y, z, yaw, pitch);
        }catch(IOException e){
            e.printStackTrace();
        }

        return new Home(player, name, location);
    }

    public void save(){
        File file = Home.getHomeFile(this.owner, this.name);

        try{
            FileWriter write = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(write);

            writer.write(this.toString());

            writer.close();
            write.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static boolean exists(Player player, String name){
        return Home.getHomeFile(player, name).exists();
    }

    public String getName(){ return name; }
    public Location getLocation(){ return location; }

    @Override
    public String toString() {
        return this.location.getX()
                + " " + this.location.getY()
                + " " + this.location.getZ()
                + " " + this.location.getPitch()
                + " " + this.location.getYaw()
                + " " + this.location.getWorld().getName();
    }

    public static File getFolderOf(Player player){ return new File(Folder.home, player.getUniqueId().toString()); }
    public static File getHomeFile(Player player, String homeName){
        File folder = getFolderOf(player);
        if(!folder.exists()) folder.mkdir();

        return new File(folder, homeName);
    }

    public static Home[] getAllHomesOf(Player player){
        File folder = Home.getFolderOf(player);
        if(!folder.exists()) folder.mkdir();
        Home[] homes = new Home[folder.listFiles().length];

        for(int i = 0; i < homes.length; i++) {
            homes[i] = Home.load(player, folder.listFiles()[i].getName());
        }

        return homes;
    }
}