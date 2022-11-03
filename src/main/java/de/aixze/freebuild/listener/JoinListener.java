package de.aixze.freebuild.listener;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class JoinListener implements Listener {

    @EventHandler
    public void handleJoin(final PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player p = e.getPlayer();

        if (!SystemAPI.isVanished(p)) {
            Bukkit.broadcastMessage(Main.getInstance().prefix + p.getDisplayName() + " §7hat den Server §abetreten§7.");
        }
    }
}
