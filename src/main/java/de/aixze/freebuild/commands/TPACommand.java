package de.aixze.freebuild.commands;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.main.Main;
import de.aixze.freebuild.manager.TPAManager;
import org.bukkit.Bukkit;
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

public class TPACommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage(SystemAPI.getNoConsoleMessage());

            return true;
        }

        if(args.length != 1 && args.length != 2) {
            p.sendMessage(Main.getInstance().prefix + "§6Informationen zur Verwendung des TPA-Befehls:");
            p.sendMessage("");
            p.sendMessage(Main.getInstance().prefix + "§8• §b/tpa §7<Spieler> §8» §7Sendet dem Spieler eine TPA-Anfrage.");
            p.sendMessage(Main.getInstance().prefix + "§8• §b/tpa §7accept <Spieler> §8» §7Akzeptiert die TPA-Anfrage eine Spielers.");
            p.sendMessage(Main.getInstance().prefix + "§8• §b/tpa §7decline <Spieler> §8» §7Lehnt die TPA-Anfrage eine Spielers ab.");
            p.sendMessage("");

            return true;
        }

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if(target == null){
                p.sendMessage(Main.getInstance().prefix + "§cDieser Spieler ist nicht online.");

                return true;
            }

            TPAManager.getTPAManager(target).sendTPARequest(p);
        }

        if(args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "accept" -> {
                    TPAManager.getTPAManager(p).accept(args[1]);
                }

                case "decline" -> {
                    TPAManager.getTPAManager(p).decline(args[1]);
                }
            }
        }

        return false;
    }
}
