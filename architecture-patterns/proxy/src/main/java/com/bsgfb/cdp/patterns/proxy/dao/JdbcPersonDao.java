package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.mapper.PersonMapper;
import com.bsgfb.cdp.patterns.proxy.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Jdbc implementation of PersonDao
 */
public class JdbcPersonDao implements PersonDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcPersonDao.class);
    private static final String SQL_PERSON_READ = "sql.person.read";
    private static final PersonMapper personMapper = new PersonMapper();

    private final String readPersonQuery;

    private DataSource dataSource;

    /**
     * @param dataSource datasource to work with database
     * @param queries list of queries to make request to database
     */
    public JdbcPersonDao(final Properties queries, final DataSource dataSource) {
        readPersonQuery = queries.getProperty(SQL_PERSON_READ);
        this.dataSource = dataSource;
    }

    @Override
    public Person readPerson(final String username) throws SQLException {
        LOGGER.debug("readPerson with username [{}]", username);
        Person person;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readPersonQuery)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    person = personMapper.get(resultSet);
                }
            }
        }

        LOGGER.debug("readPerson person [{}]", person.toString());
        return person;
    }
}
