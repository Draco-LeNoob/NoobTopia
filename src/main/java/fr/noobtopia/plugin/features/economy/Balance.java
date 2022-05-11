package fr.noobtopia.plugin.features.economy;

import fr.noobtopia.plugin.engine.io.Folder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;

public class Balance {
    private final Player owner;
    private double balance;

    public Balance(Player owner, double balance){
        this.owner = owner;
        this.balance = balance;
    }

    public static Balance load(File file){
        double balance = 0;

        try{
            FileReader read = new FileReader(file);
            BufferedReader reader = new BufferedReader(read);

            String line = reader.readLine();

            reader.close();
            read.close();

            balance = Double.parseDouble(line);
        }catch(IOException e){
            e.printStackTrace();
        }

        return new Balance(Bukkit.getPlayer(file.getName()), balance);
    }

    public void add(double add){
        this.balance += add;
        this.save();
    }

    public void subtract(double subtract){
        this.balance -= subtract;
        this.save();
    }

    public void save(){
        File save = new File(Folder.economy, this.owner.getName());

        try{
            FileWriter write = new FileWriter(save);
            BufferedWriter writer = new BufferedWriter(write);

            writer.write("" + this.balance);

            writer.close();
            write.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public Player getOwner(){ return owner; }
    public double getBalance(){ return balance; }
}