package fr.noobtopia.plugin.skills;

import fr.noobtopia.plugin.utils.Folders;
import org.bukkit.entity.Player;

import java.io.*;

public class Skill {
    public static final int WALK  = 0;
    public static final int SWORD = 1;

    private Player owner;
    private int skill;
    private int level;
    private double xp;

    public Skill(Player owner, int skill, int level, double xp){
        this.owner = owner;
        this.skill = skill;
        this.level = level;
        this.xp = xp;
    }

    public void save(){
        File folder = getSkillFolder(this.owner, this.skill);

        if(!folder.exists()) folder.mkdir();

        File levelFile = new File(folder, "level");
        File xpFile = new File(folder, "xp");

        try{
            FileWriter levelWrite = new FileWriter(levelFile);
            BufferedWriter levelWriter = new BufferedWriter(levelWrite);

            levelWriter.write("" + this.level);

            levelWriter.close();
            levelWrite.close();

            FileWriter xpWrite = new FileWriter(xpFile);
            BufferedWriter xpWriter = new BufferedWriter(xpWrite);

            xpWriter.write("" + this.xp);

            xpWriter.close();
            xpWrite.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Skill load(Player player, int skill){
        File folder = getSkillFolder(player, skill);
        File levelFile = new File(folder, "level");
        File xpFile = new File(folder, "xp");

        int level = 0;
        double xp = 0;

        try{
            FileReader levelRead = new FileReader(levelFile);
            BufferedReader levelReader = new BufferedReader(levelRead);

            level = Integer.parseInt(levelReader.readLine());

            levelReader.close();
            levelRead.close();

            FileReader xpRead = new FileReader(xpFile);
            BufferedReader xpReader = new BufferedReader(xpRead);

            xp = Double.parseDouble(xpReader.readLine());

            xpReader.close();
            xpRead.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return new Skill(player, skill, level, xp);
    }

    public static File getSkillsFolder(Player player){ return new File(Folders.skills, player.getName() + "/"); }
    public static File getSkillFolder(Player player, int skill){ return new File(getSkillsFolder(player), "" + skill); }
}