package de.aixze.freebuild.listener;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.freebuild.main.Main;
import de.aixze.freebuild.manager.TPAManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class QuitListener implements Listener {

    @EventHandler
    public void handleQuit(final PlayerQuitEvent e) {
        e.setQuitMessage(null);

        Player p = e.getPlayer();

        if (!SystemAPI.isVanished(p)) {
            Bukkit.broadcastMessage(Main.getInstance().prefix + p.getDisplayName() + " §7hat den Server §cverlassen§7.");
        }

        TPAManager.getTPAManager(p).remove();
    }
}
