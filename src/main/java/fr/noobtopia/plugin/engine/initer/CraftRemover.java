package fr.noobtopia.plugin.engine.initer;

import fr.noobtopia.plugin.NoobPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class CraftRemover {
    public static void removeCrafts(){
        remove(Material.ENCHANTING_TABLE);
    }

    private static void remove(Material item){
        Iterator<Recipe> iterator = NoobPlugin.instance.getServer().recipeIterator();
        Recipe recipe;

        while(iterator.hasNext()){
            recipe = iterator.next();

            if(recipe != null && recipe.getResult().getType() == item) iterator.remove();
        }
    }
}