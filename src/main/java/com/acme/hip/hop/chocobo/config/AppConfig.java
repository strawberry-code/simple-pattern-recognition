package com.acme.hip.hop.chocobo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AppConfig manages application properties loaded from the configuration file.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private static final Logger logger = LogManager.getLogger(AppConfig.class);

    private String name;
    private String combinationMode;

    /**
     * Constructor for AppConfig.
     * Logs initialization.
     */
    public AppConfig() {
        logger.info("Initializing AppConfig");
    }

    /**
     * Gets the application name.
     * @return the name of the application
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the application name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the combination mode of the application.
     * @return the combination mode
     */
    public String getCombinationMode() {
        return combinationMode;
    }

    /**
     * Sets the combination mode of the application.
     * @param combinationMode the combination mode to set
     */
    public void setCombinationMode(String combinationMode) {
        this.combinationMode = combinationMode;
    }

}