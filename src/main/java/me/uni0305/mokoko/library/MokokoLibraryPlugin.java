package me.uni0305.mokoko.library;

import lombok.Getter;
import me.uni0305.mokoko.library.configuration.HikariDataSourceConfig;
import me.uni0305.mokoko.library.configuration.YamlConfigurator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class MokokoLibraryPlugin extends JavaPlugin {
    @Getter
    private static YamlConfigurator yamlConfigurator;

    @Getter
    private static HikariDataSourceConfig hikariDataSourceConfig;

    @Override
    public void onEnable() {
        yamlConfigurator = new YamlConfigurator(this, "config.yml");
        yamlConfigurator.saveDefaultConfig();
        yamlConfigurator.reloadConfig();

        if (yamlConfigurator.getConfig().contains("database", true)) {
            ConfigurationSection config = yamlConfigurator.getConfig().getConfigurationSection("database");
            if (config == null) {
                getSLF4JLogger().error("Database configuration is invalid, skipping HikariCP initialization.");
                return;
            }

            getSLF4JLogger().info("Database configuration found, initializing HikariCP...");
            hikariDataSourceConfig = new HikariDataSourceConfig(config);
            hikariDataSourceConfig.start();
        } else {
            getSLF4JLogger().warn("Database configuration not found, skipping HikariCP initialization.");
        }
    }

    @Override
    public void onDisable() {
        if (hikariDataSourceConfig != null) hikariDataSourceConfig.shutdown();
    }
}
