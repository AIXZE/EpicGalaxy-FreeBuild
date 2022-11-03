package de.aixze.freebuild.utils;

import de.aixze.freebuild.locations.SpawnLocations;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class WorldUtils {

    public static void loadWorldSettings() {
        World w = Bukkit.getWorld("world");

        if(w != null) {
            w.setSpawnLocation(SpawnLocations.getSpawnLocation());
            w.setKeepSpawnInMemory(true);
            w.setDifficulty(Difficulty.HARD);
            w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
    }

    public static String getWorldName(final World w) {
        switch (w.getName()) {
            case "world" -> {
                return "Overworld";
            }

            case "world_nether" -> {
                return "Nether";
            }

            case "world_the_end" -> {
                return "End";
            }

            default -> {
                return w.getName();
            }
        }
    }
}
