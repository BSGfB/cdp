package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.mapper.PersonMapper;
import com.bsgfb.cdp.patterns.proxy.model.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcPersonDao implements PersonDao {
    private static final String SQL_PERSON_READ = "sql.person.read";
    private static final PersonMapper personMapper = new PersonMapper();

    private final String readPersonQuery;

    private DataSource dataSource;

    public JdbcPersonDao(final Properties queries, final DataSource dataSource) {
        readPersonQuery = queries.getProperty(SQL_PERSON_READ);
        this.dataSource = dataSource;
    }

    @Override
    public Person readPerson(final String username) throws SQLException {
        Person person;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readPersonQuery)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    person = personMapper.get(resultSet);
                }
            }
        }

        return person;
    }
}
