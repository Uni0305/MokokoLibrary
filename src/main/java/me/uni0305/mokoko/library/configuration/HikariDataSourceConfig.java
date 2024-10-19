package me.uni0305.mokoko.library.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class HikariDataSourceConfig {
    private final HikariConfig config;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HikariDataSource dataSource = null;

    public HikariDataSourceConfig(@NotNull FileConfiguration configuration) throws IllegalArgumentException {
        ConfigurationSection section = configuration.getConfigurationSection("database");
        if (section == null) throw new IllegalArgumentException("Database configuration not found.");

        String host = section.getString("host", "localhost");
        int port = section.getInt("port", 3306);
        String database = section.getString("database", "minecraft");
        String username = section.getString("username", "root");
        String password = section.getString("password", "example");
        int maximumPoolSize = section.getInt("maximum-pool-size", 10);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        this.config = config;
    }

    public void start() {
        try {
            dataSource = new HikariDataSource(config);
            logger.info("HikariCP has been initialized.");
        } catch (Exception e) {
            logger.error("Failed to initialize HikariCP.", e);
        }
    }

    public void shutdown() {
        if (dataSource == null) return;
        try {
            dataSource.close();
            logger.info("HikariCP has been shutdown.");
        } catch (Exception e) {
            logger.error("Failed to shutdown HikariCP.", e);
        }
    }

    public @NotNull Connection getConnection() throws Exception {
        if (dataSource == null) throw new IllegalStateException("HikariCP is not initialized.");
        return dataSource.getConnection();
    }
}
