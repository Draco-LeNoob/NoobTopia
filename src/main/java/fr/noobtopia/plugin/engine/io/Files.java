package fr.noobtopia.plugin.engine.io;

import java.io.File;
import java.io.IOException;

public class Files {
    public static final File messages = new File(Folder.noobtopia, "messages.json");

    public static void create(){
        try{
            if(!messages.exists()) messages.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}