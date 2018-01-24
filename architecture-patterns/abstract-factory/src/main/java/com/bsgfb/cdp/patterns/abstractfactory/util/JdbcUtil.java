package com.bsgfb.cdp.patterns.abstractfactory.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.IntStream;

/**
 * Contains jdbc util methods to facilitate humans life
 */
public class JdbcUtil {

    /**
     * Populate database with start values
     *
     * @param dataSource is used to connect to database
     * @param queries to create requests to database
     * @throws SQLException if sql exception occurs
     */
    public static void populateDatabase(DataSource dataSource, Properties queries) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement create = connection.prepareStatement(queries.getProperty("sql.person.create-table"))) {
                create.execute();
            }

            try (PreparedStatement insert = connection.prepareStatement(queries.getProperty("sql.person.save3"))) {
                IntStream.range(0, 3).forEach(index -> {
                    try {
                        insert.setString(index * 2 + 1, "test_user_" + index);
                        insert.setString(index * 2 + 2, "password" + index);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                insert.execute();
            }
            connection.commit();
        }

    }
}
