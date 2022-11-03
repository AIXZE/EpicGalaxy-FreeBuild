package de.aixze.freebuild.listener;

import de.aixze.freebuild.locations.SpawnLocations;
import de.aixze.freebuild.main.Main;
import de.aixze.freebuild.utils.SaveChestUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class DeathListener implements Listener {

    @EventHandler
    public void handleDeath(final PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = p.getKiller();

        if(killer != null && p != killer) {
            e.setDeathMessage(Main.getInstance().prefix + p.getDisplayName() + " §7wurde von §a" + killer.getDisplayName() + " §7getötet.");
        } else {
            e.setDeathMessage(Main.getInstance().prefix + p.getDisplayName() + " §7ist gestorben.");
        }

        if(!(SpawnLocations.isInsideSpawnArea(p) || SpawnLocations.isInsideSpawnArea(p))) {
            e.getDrops().clear();

            SaveChestUtils.createSaveChest(p);
        }
    }
}
