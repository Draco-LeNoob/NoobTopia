package fr.noobtopia.plugin.permission;

import fr.noobtopia.plugin.NoobPlugin;
import fr.noobtopia.plugin.io.Folders;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    public static final String TRAVELER = "noobtopia.traveler";
    public static final String ECONOMY = "noobtopia.economy";

    public static File getFolderOf(Player player){
        File folder = new File(Folders.permissions, player.getName() + "/");
        if(folder != null && !folder.exists()) folder.mkdir();

        return folder;
    }

    public static File getFileOf(Player player, String permission){ return new File(getFolderOf(player), permission); }
    public static void addPermission(Player player, String permission){ PermissionManager.setPermission(player, permission, true); }
    public static void setPermission(Player player, String permission, boolean setPermission){
        File permissionFile = getFileOf(player, permission);

        if(permissionFile == null) return;

        if(setPermission){
            try {
                permissionFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            permissionFile.delete();
        }

        PermissionManager.reloadPermissions(player);
    }

    public static List<String> getExistingPermissions(){ return NoobPlugin.instance.getConfig().getStringList("permissions"); }
    public static List<String> getPermissions(Player player){
        List<String> permissions = new ArrayList<>();

        for(File file : getFolderOf(player).listFiles()){
            permissions.add(file.getName());
        }

        return permissions;
    }

    public static void reloadPermissions(Player player){
        PermissionAttachment attachment = player.addAttachment(NoobPlugin.instance);

        PermissionManager.getExistingPermissions().forEach(permission ->  attachment.setPermission(permission, false));
        PermissionManager.getPermissions(player).forEach(permission -> attachment.setPermission(permission, true));

        player.recalculatePermissions();
        player.updateCommands();
    }
}