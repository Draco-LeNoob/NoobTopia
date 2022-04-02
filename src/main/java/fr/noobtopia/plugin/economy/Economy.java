package fr.noobtopia.plugin.economy;

import fr.noobtopia.plugin.utils.Folders;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;

public class Economy {
    public static void init(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(Economy.needBalance(player)) Economy.registerBalance(player);
        }
    }

    public static boolean needBalance(Player player){
        return !new File(Folders.economy, player.getName()).exists();
    }

    public static void pay(Player from, Player to, double amount){
        Economy.getBalanceOf(from).subtract(amount);
        Economy.getBalanceOf(to).add(amount);
    }

    public static Balance getBalanceOf(Player player){
        File file = new File(Folders.economy, player.getName());
        String balanceStr = "";

        try{
            FileReader read = new FileReader(file);
            BufferedReader reader = new BufferedReader(read);

            balanceStr = reader.readLine();

            reader.close();
            read.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        double balance = Double.parseDouble(balanceStr);

        return new Balance(player, balance);
    }
    public static void registerBalance(Player player){
        new Balance(player, 0).save();
    }
}