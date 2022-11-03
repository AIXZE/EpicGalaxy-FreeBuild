package de.aixze.freebuild.listener;

import de.aixze.freebuild.locations.SpawnLocations;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class GlideListener implements Listener {

    //List<UUID>
    private final List<UUID> glideList = new ArrayList<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void handleMove(final PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (SpawnLocations.isInsideSpawnArea(p) && p.getFallDistance() >= 3) {
            this.glideList.add(p.getUniqueId());

            p.setGliding(true);
        } else {
            if (p.isOnGround() || p.getLocation().getBlock().getType() == Material.WATER) {
                this.glideList.remove(p.getUniqueId());

                p.setGliding(false);
            }
        }
    }

    @EventHandler
    public void handleGlide(final EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player p && this.glideList.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            e.setCancelled(this.glideList.contains(p.getUniqueId()));
        }
    }
}
