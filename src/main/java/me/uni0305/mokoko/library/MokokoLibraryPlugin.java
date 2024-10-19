package me.uni0305.mokoko.library;

import lombok.Getter;
import me.uni0305.mokoko.library.configuration.HikariDataSourceConfig;
import me.uni0305.mokoko.library.configuration.YamlConfigurator;
import org.bukkit.plugin.java.JavaPlugin;

public class MokokoLibraryPlugin extends JavaPlugin {
    @Getter
    private static YamlConfigurator yamlConfigurator;

    @Getter
    private static HikariDataSourceConfig hikariDataSourceConfig;

    @Override
    public void onEnable() {
        yamlConfigurator = new YamlConfigurator(this, "config.yml");
        hikariDataSourceConfig = new HikariDataSourceConfig(yamlConfigurator.getConfig());
        hikariDataSourceConfig.start();
    }

    @Override
    public void onDisable() {
        hikariDataSourceConfig.shutdown();
    }
}
