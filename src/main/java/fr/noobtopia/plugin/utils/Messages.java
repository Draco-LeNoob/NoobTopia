package fr.noobtopia.plugin.utils;

import fr.noobtopia.plugin.io.Files;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Messages {
    private static HashMap<String, String> messages;

    public static void init(){
        messages = new HashMap<String, String>();

        JSONParser parser = new JSONParser();

        try{
            FileInputStream fileInput = new FileInputStream(Files.messages);
            InputStreamReader inputStream = new InputStreamReader(fileInput, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStream);

            Object object = parser.parse(reader);
            JSONObject json = (JSONObject) object;

            JSONArray messages = (JSONArray) json.get("messages");

            for(Object o : messages){
                String message = (String) o;
                String key = message.split(":")[0];
                String content = message.split(":")[1];

                Messages.messages.put(key, content);
            }

            reader.close();
            inputStream.close();
            fileInput.close();
        }catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return messages.getOrDefault(key, "MESSAGE NOT FOUND ERROR");
    }
}