package de.aixze.freebuild.commands;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.main.Main;
import de.aixze.freebuild.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
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

public class PositionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(SystemAPI.getNoConsoleMessage());

            return true;
        }

        String world = WorldUtils.getWorldName(p.getWorld());
        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();

        Bukkit.broadcastMessage(Main.getInstance().prefix + "§aPosition §7von §a" + p.getDisplayName() + "§7: §a" + world + " §7[§a" + x + "§7/§a" + y + "§7/§a" + z + "§7]");

        return true;
    }
}
