package de.aixze.freebuild.locations;

import de.aixze.freebuild.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JavaDoc this file!
 * Created: 01.11.2022
 *
 * @author AIXZE
 */

public class SpawnLocations {

    //Location
    private static Location islandLocation;
    private static Location spawnLocation;
    private static Location spawnPointLocation;

    public static void setLocation(final Player p, final String locationName) {
        File f = new File("plugins/FreeBuild/Locations", "Locations.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        Location loc = p.getLocation();
        String sLoc = Objects.requireNonNull(loc.getWorld(), "World is null!").getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();

        List<String> locList = new ArrayList<>();
        locList.add(sLoc);

        cfg.set(locationName, locList);

        try {
            cfg.save(f);
        } catch (IOException ex) {
            p.sendMessage(Main.getInstance().prefix + "§cEin Fehler beim Setzen der Position ist aufgetreten.");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);

            ex.printStackTrace();

            return;
        }

        p.sendMessage(Main.getInstance().prefix + "§7Die Position für die Position §b" + locationName + " §7wurde erfolgreich gesetzt.");
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
    }

    private static Location loadLocation(final String locationName) {
        File f = new File("plugins/FreeBuild/Locations", "Locations.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        List<String> locationList = cfg.getStringList(locationName);

        for (String locs : locationList) {
            String[] sLoc = locs.split(";");

            return new Location(Bukkit.getWorld(sLoc[0]), Double.parseDouble(sLoc[1]), Double.parseDouble(sLoc[2]), Double.parseDouble(sLoc[3]), Float.parseFloat(sLoc[4]), Float.parseFloat(sLoc[5]));
        }

        return null;
    }

    public static void loadLocations() {
        islandLocation = loadLocation("player_Island");
        spawnLocation = loadLocation("player_Spawn");
        spawnPointLocation = loadLocation("point_Spawn");
    }

    public static Location getIslandLocation() {
        return islandLocation;
    }

    public static boolean isInsideIslandArea(final Location loc) {
        return Objects.requireNonNull(loc.getWorld(), "World is null!").getName().equals("world") && getIslandLocation().distance(loc) <= 10;
    }

    public static Location getSpawnLocation() {
        return spawnLocation;
    }

    public static Location getSpawnPointLocation() {
        return spawnPointLocation;
    }

    public static boolean isInsideSpawnArea(final Location loc) {
        return Objects.requireNonNull(loc.getWorld(), "World is null!").getName().equals("world") && getSpawnPointLocation().distance(loc) <= 30;
    }
}
