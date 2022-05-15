package fr.noobtopia.plugin.features.mana;

import fr.noobtopia.plugin.engine.io.Folder;
import org.bukkit.entity.Player;

import java.io.*;

public class Mana {
    public static void add(Player player, ManaType type, int value){ Mana.set(player, type, Mana.get(player, type) + value); }
    public static void subtract(Player player, ManaType type, int value){ Mana.set(player, type, Mana.get(player, type) - value); }

    public static void set(Player player, ManaType type, int value){
        try(FileWriter write = new FileWriter(getFileOf(player, type))){
            BufferedWriter writer = new BufferedWriter(write);

            writer.write("" + value);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int get(Player player, ManaType type){
        File file = getFileOf(player, type);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int level = 0;

        try(FileReader read = new FileReader(file)){
            BufferedReader reader = new BufferedReader(read);

            try { level = Integer.parseInt(reader.readLine()); } catch (Exception e){ level = 0; }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return level;
    }

    public static File getFolderOf(Player player){
        File folder = new File(Folder.mana, player.getUniqueId().toString());
        if(!folder.exists()) folder.mkdir();

        return folder;
    }
    public static File getFileOf(Player player, ManaType type){ return new File(getFolderOf(player), type.name()); }
}