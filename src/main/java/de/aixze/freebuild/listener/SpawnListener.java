package de.aixze.freebuild.listener;

import de.aixze.epicgalaxy.api.SystemAPI;
import de.aixze.epicgalaxy.utils.TitleUtils;
import de.aixze.freebuild.locations.SpawnLocations;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class SpawnListener implements Listener {

    @EventHandler
    public void handleBlockBreak(final BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();

        if(!SystemAPI.isBuilder(p) && (SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleBlockPlace(final BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();

        if(!SystemAPI.isBuilder(p) && (SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleChangeBlock(final EntityChangeBlockEvent e) {
        Location loc = e.getBlock().getLocation();

        if(SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleEntityDamage(final EntityDamageEvent e) {
        if(e.getEntity() instanceof Player p) {
            Location loc = p.getLocation();

            if(!SystemAPI.isBuilder(p) && (SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void handleExplode(final EntityExplodeEvent e) {
        Location loc = e.getLocation();

        if(SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleInteract(final PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();

        if(!SystemAPI.isBuilder(p) && (SpawnLocations.isInsideIslandArea(loc) || SpawnLocations.isInsideSpawnArea(loc))) {
            e.setCancelled(true);
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void handleMove(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();

        if(SpawnLocations.isInsideIslandArea(loc) && loc.distance(SpawnLocations.getIslandLocation()) <= 1.0 && loc.clone().subtract(0, 1, 0).getBlock().getType() == Material.BEACON) {
            p.teleport(SpawnLocations.getSpawnLocation());
            p.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 2.0F);
            p.playEffect(loc, Effect.ENDER_SIGNAL, 1);

            TitleUtils.sendActionBar(p, "§8» §aDu wurdest zum Spawn teleportiert.");
        }
    }
}
