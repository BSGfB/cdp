package com.bsgfb.cdp.patterns.abstractfactory.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class HikariH2DataSourceFactory implements DataSourceFactory {
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";
    public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    public static final String JDBC_POOL_SIZE = "jdbc.poolSize";

    @Override
    public DataSource createDataSource(final Properties properties) {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(properties.getProperty(JDBC_DRIVER_CLASS_NAME));
        ds.setJdbcUrl(properties.getProperty(JDBC_URL));
        ds.setUsername(properties.getProperty(JDBC_USERNAME));
        ds.setPassword(properties.getProperty(JDBC_PASSWORD));
        ds.setMaximumPoolSize(Integer.valueOf(properties.getProperty(JDBC_POOL_SIZE, "5")));

        return ds;
    }
}
