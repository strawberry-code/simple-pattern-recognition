package com.acme.hip.hop.chocobo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private static final Logger logger = LogManager.getLogger(AppConfig.class);

    private String name;
    private String combinationMode;

    public AppConfig() {
        logger.info("Initializing AppConfig");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCombinationMode() {
        return combinationMode;
    }

    public void setCombinationMode(String combinationMode) {
        this.combinationMode = combinationMode;
    }

}