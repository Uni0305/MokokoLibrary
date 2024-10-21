package me.uni0305.mokoko.library.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class handles the configuration of YAML files for a Bukkit plugin.
 */
public class YamlConfigurator {
    private final JavaPlugin plugin;
    private final String filename;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private File file = null;
    private FileConfiguration configuration = null;

    /**
     * Constructs a new YamlConfigurator.
     *
     * @param plugin   the JavaPlugin instance
     * @param filename the name of the YAML file
     */
    public YamlConfigurator(@NotNull JavaPlugin plugin, @NotNull String filename) {
        this.plugin = plugin;
        this.filename = filename;
    }

    /**
     * Reloads the configuration from the YAML file.
     */
    public void reloadConfig() {
        if (file == null) file = new File(plugin.getDataFolder(), filename);
        try {
            configuration = YamlConfiguration.loadConfiguration(file);
            logger.info("Config loaded from {}", file);
        } catch (Exception e) {
            logger.error("Could not load config from {}", file, e);
        }

        InputStream resource = plugin.getResource(filename);
        if (resource == null) {
            logger.warn("Skipping default config load, resource not found: {}", filename);
            return;
        }

        try {
            InputStreamReader reader = new InputStreamReader(resource);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(reader);
            configuration.setDefaults(defaultConfig);
            logger.info("Default config loaded from {}", filename);
        } catch (Exception e) {
            logger.error("Could not load default config from {}", filename, e);
        }
    }

    /**
     * Gets the configuration.
     *
     * @return the FileConfiguration instance
     */
    public @NotNull FileConfiguration getConfig() {
        if (configuration == null) reloadConfig();
        return configuration;
    }

    /**
     * Saves the current configuration to the YAML file.
     */
    public void saveConfig() {
        if (file == null || configuration == null) {
            logger.warn("Skipping config save, file or configuration is null");
            return;
        }

        try {
            getConfig().save(file);
            logger.info("Config saved to {}", file);
        } catch (IOException e) {
            logger.error("Could not save config to {}", file, e);
        }
    }

    /**
     * Saves the default configuration to the YAML file if it does not already exist.
     */
    public void saveDefaultConfig() {
        if (file == null) file = new File(plugin.getDataFolder(), filename);
        if (file.exists()) {
            logger.warn("Skipping default config save, file already exists: {}", file);
            return;
        }

        try {
            plugin.saveResource(filename, false);
            logger.info("Default config saved to {}", file);
        } catch (Exception e) {
            logger.error("Could not save default config to {}", file, e);
        }
    }
}
