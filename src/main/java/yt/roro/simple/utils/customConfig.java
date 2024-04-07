package yt.roro.simple.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import yt.roro.simple.SimpleFly;

import java.io.File;

public class customConfig {
    private final static customConfig instance = new customConfig();
    private File file;
    private YamlConfiguration config;
    private boolean commandEnabled;

    private customConfig(){}

    public void load() {
        this.file = new File(SimpleFly.getInstance().getDataFolder(), "config.yaml");
        if(!file.exists())
            SimpleFly.getInstance().saveResource("config.yaml", false);
        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch(Exception err) {
            err.printStackTrace();

        }

        commandEnabled = config.getBoolean("commandEnabled");
    }

    public void save() {
        try {
            config.save(file);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public YamlConfiguration get() {
        return config;
    }

    public static customConfig getInstance() {
        return instance;
    }
}
