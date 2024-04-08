package yt.roro.simple;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import yt.roro.simple.commands.fly;
import yt.roro.simple.commands.simpleflyReload;
import yt.roro.simple.utils.customConfig;

public final class SimpleFly extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("[Simple Fly] has been Enabled!");
        customConfig.getInstance().load();
        Bukkit.getLogger().info(("[Simple Fly] Config has successfully been loaded!"));

        getCommand("fly").setExecutor(new fly());
        getCommand("simplefly-reload").setExecutor(new simpleflyReload());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[Simple Fly] has been Disabled!");
    }

    public static SimpleFly getInstance() {
        return getPlugin(SimpleFly.class);
    }
}
