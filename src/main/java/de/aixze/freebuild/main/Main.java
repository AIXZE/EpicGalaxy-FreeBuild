package de.aixze.freebuild.main;

import de.aixze.freebuild.commands.PositionCommand;
import de.aixze.freebuild.commands.SetCommand;
import de.aixze.freebuild.commands.TPACommand;
import de.aixze.freebuild.listener.*;
import de.aixze.freebuild.locations.SpawnLocations;
import de.aixze.freebuild.permissions.PermissionLoader;
import de.aixze.freebuild.utils.SaveChestUtils;
import de.aixze.freebuild.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    //String
    public final String prefix = "§8» §aFreeBuild §8▏ §r";

    //Instance
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerListener();
        registerUtils();
    }

    @Override
    public void onDisable() {
        SaveChestUtils.removeAllSaveChests();
    }

    private void registerCommands() {
        getCommand("position").setExecutor(new PositionCommand());
        getCommand("set").setExecutor(new SetCommand());
        getCommand("tpa").setExecutor(new TPACommand());
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new GlideListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new SaveChestListener(), this);
        pm.registerEvents(new SpawnListener(), this);
    }

    private void registerUtils() {
        PermissionLoader.loadPermissions();
        SpawnLocations.loadLocations();
        WorldUtils.loadWorldSettings();
    }

    public static Main getInstance() {
        return instance;
    }
}
