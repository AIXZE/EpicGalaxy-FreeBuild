package de.aixze.freebuild.commands;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.locations.SpawnLocations;
import de.aixze.freebuild.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage(SystemAPI.getNoConsoleMessage());

            return true;
        }

        if(!p.hasPermission("admin")){
            sender.sendMessage(SystemAPI.getNoPermissionMessage());

            return true;
        }

        if (args.length != 1) {
            p.sendMessage(Main.getInstance().prefix + "§cVerwendung: /set <Position>");
        }

        if (args.length == 1) {
            String locName = args[0];

            if (locName.equalsIgnoreCase("island")) {
                SpawnLocations.setLocation(p, "player_Island");
            } else if (locName.equalsIgnoreCase("spawn")) {
                SpawnLocations.setLocation(p, "player_Spawn");
            } else {
                p.sendMessage(Main.getInstance().prefix + "§cDiese Location existiert nicht.");
            }
        }

        return true;
    }
}
