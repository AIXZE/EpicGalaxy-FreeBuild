package de.aixze.freebuild.utils;

import de.aixze.freebuild.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class SaveChestUtils {

    //HashMap<Block, Inventory>
    private static final HashMap<Block, Inventory> saveChestMap = new HashMap<>();

    public static void createSaveChest(final Player p) {
        Block saveChest = p.getWorld().getBlockAt(p.getLocation().clone().add(0, 0.5, 0));
        saveChest.setType(Material.CHEST);

        Inventory chestInventory = Bukkit.createInventory(null, 54, "§8» §r" + p.getDisplayName() + "'s §7SaveChest");
        chestInventory.clear();

        for (ItemStack items : p.getInventory().getContents()) {
            if(items != null) {
                chestInventory.addItem(items);
            }
        }

        saveChestMap.put(saveChest, chestInventory);

        String world = WorldUtils.getWorldName(p.getWorld());
        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();

        p.sendMessage(Main.getInstance().prefix + "§cEine §eSaveChest §cmit deinen Items wurde an deinem Todespunkt erstellt.");
        p.sendMessage(Main.getInstance().prefix + "§aPosition§7: §a" + world + " §7[§a" + x + "§7/§a" + y + "§7/§a" + z + "§7]");

        Main.getInstance().getLogger().log(Level.INFO, "SaveChest von " + p.getName() + ": Welt: " + world + " [" + x + "/" + y + "/" + z + "]");
    }

    public static boolean isSaveChest(final Block b) {
        return saveChestMap.containsKey(b);
    }

    public static Inventory getSaveChestInventory(final Block b) {
        return saveChestMap.get(b);
    }

    public static void removeSaveChest(final Block b) {
        saveChestMap.remove(b);
    }

    public static void removeAllSaveChests() {
        for (Block saveChests : saveChestMap.keySet()) {
            saveChests.setType(Material.AIR);
        }
    }
}
