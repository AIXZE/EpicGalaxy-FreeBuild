package de.aixze.freebuild.listener;

import de.aixze.freebuild.utils.SaveChestUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class SaveChestListener implements Listener {

    @EventHandler
    public void handleBreak(final BlockBreakEvent e) {
        Block b = e.getBlock();

        if(b.getType() == Material.CHEST && SaveChestUtils.isSaveChest(b)) {
            b.setType(Material.AIR);

            for (ItemStack items : SaveChestUtils.getSaveChestInventory(b).getContents()) {
                b.getWorld().dropItemNaturally(b.getLocation(), items);
            }

            SaveChestUtils.removeSaveChest(b);
        }
    }

    @EventHandler
    public void handleInteract(final PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null) {
            try {
                Block b = e.getClickedBlock();

                if(b.getType() == Material.CHEST && SaveChestUtils.isSaveChest(b)) {
                    e.setCancelled(true);

                    p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 1.0F);
                    p.openInventory(SaveChestUtils.getSaveChestInventory(b));
                }
            } catch (NullPointerException ignored) {
            }
        }
    }

    @EventHandler
    public void handleBlockExplode(final BlockExplodeEvent e) {
        if(SaveChestUtils.isSaveChest(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleEntityExplode(final EntityExplodeEvent e) {
        for (Block b : e.blockList()) {
            if(SaveChestUtils.isSaveChest(b)) {
                e.setCancelled(true);
            }
        }
    }
}