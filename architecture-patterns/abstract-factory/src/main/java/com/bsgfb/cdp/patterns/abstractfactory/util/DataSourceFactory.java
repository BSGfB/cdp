package com.bsgfb.cdp.patterns.abstractfactory.util;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Used to create different configurations of data sources
 */
public interface DataSourceFactory {

    /**
     * Creates data source with specific properties from properties
     * @param properties to create dataSource
     * @return new DataSource
     */
    DataSource createDataSource(Properties properties);
}
