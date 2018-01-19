package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.bsgfb.cdp.patterns.abstractfactory.util.DataSourceFactory;
import com.bsgfb.cdp.patterns.abstractfactory.util.HikariDataSourceFactory;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FactoryConfiguration {

    @Builder.Default
    private String dbProperties = "database.properties";

    @Builder.Default
    private String dbQueries = "queries.properties";

    @Builder.Default
    private Integer backupTime = 10;

    @Builder.Default
    private String fileLocation = "";

    @Builder.Default
    private DataSourceFactory dataSourceFactory = new HikariDataSourceFactory();
}
