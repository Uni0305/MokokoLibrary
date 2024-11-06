package me.uni0305.mokoko.library.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * Configuration class for setting up and managing a HikariCP data source.
 */
public class HikariDataSourceConfig {
    private final HikariConfig config;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HikariDataSource dataSource = null;

    /**
     * Constructs a new HikariDataSourceConfig with the specified configuration.
     *
     * @param configuration the configuration to use for setting up the data source
     * @throws IllegalArgumentException if the database configuration section is not found
     */
    public HikariDataSourceConfig(@NotNull ConfigurationSection configuration) throws IllegalArgumentException {
        String host = configuration.getString("host", "localhost");
        int port = configuration.getInt("port", 3306);
        String database = configuration.getString("database", "minecraft");
        String username = configuration.getString("username", "root");
        String password = configuration.getString("password", "example");
        int maximumPoolSize = configuration.getInt("maximum-pool-size", 10);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        this.config = config;
    }

    /**
     * Initializes the HikariCP data source.
     */
    public void start() {
        try {
            dataSource = new HikariDataSource(config);
            logger.info("HikariCP has been initialized.");
        } catch (Exception e) {
            logger.error("Failed to initialize HikariCP.", e);
        }
    }

    /**
     * Shuts down the HikariCP data source.
     */
    public void shutdown() {
        if (dataSource == null) return;
        try {
            dataSource.close();
            logger.info("HikariCP has been shutdown.");
        } catch (Exception e) {
            logger.error("Failed to shutdown HikariCP.", e);
        }
    }

    /**
     * Retrieves a connection from the HikariCP data source.
     *
     * @return a connection from the data source
     * @throws Exception if the data source is not initialized or a connection cannot be retrieved
     */
    public @NotNull Connection getConnection() throws Exception {
        if (dataSource == null) throw new IllegalStateException("HikariCP is not initialized.");
        return dataSource.getConnection();
    }
}
