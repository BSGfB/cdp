package com.bsgfb.cdp.patterns.proxy.util;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";
    public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";

    public static DataSource createDataSource(Properties databaseProperties) throws ClassNotFoundException {
        Class.forName(databaseProperties.getProperty(JDBC_DRIVER_CLASS_NAME));
        return JdbcConnectionPool.create(
                databaseProperties.getProperty(JDBC_URL),
                databaseProperties.getProperty(JDBC_USERNAME),
                databaseProperties.getProperty(JDBC_PASSWORD));
    }

    public static void populateDatabase(DataSource dataSource, Properties queries) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement create = connection.prepareStatement(queries.getProperty("sql.person.create-table"))) {
                create.execute();
            }

            try (PreparedStatement insert = connection.prepareStatement(queries.getProperty("sql.person.save"))) {
                insert.setString(1, "Bob");
                insert.setString(2, "123");
                insert.execute();
            }

            try (PreparedStatement insert = connection.prepareStatement(queries.getProperty("sql.person.save"))) {
                insert.setString(1, "Siarhei");
                insert.setString(2, "qwerty");
                insert.execute();
            }


            connection.commit();
        }
    }
}
