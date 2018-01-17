package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.HikariH2DataSourceFactory;
import io.reactivex.Single;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.IntStream;

public class JdbcPersonDaoTest {

    private static PersonDao personDao;

    @BeforeClass
    public static void initDatabase() throws SQLException, IOException {
        Properties databaseProperties = readProperties("database.properties");
        Properties queries = readProperties("queries.properties");

        DataSource dataSource = new HikariH2DataSourceFactory().createDataSource(databaseProperties);

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

        personDao = new JdbcPersonDao(dataSource, queries);
    }

    @Test
    public void readPeople() {
        Assert.assertTrue(personDao.readPeople().toList().blockingGet().size() >= 3);
    }

    @Test
    public void readPerson() {
        String username = "test_user_1";
        Person person = personDao.readPerson(username).blockingGet();
        Assert.assertEquals(username, person.getUsername());
    }

    @Test
    public void writePerson() {
        Person bob = Person.builder().username("Bob").password("123").build();
        personDao.writePerson(Single.just(bob));
        Person person = personDao.readPerson(bob.getUsername()).blockingGet();

        Assert.assertEquals(bob.getUsername(), person.getUsername());
        Assert.assertEquals(bob.getPassword(), person.getPassword());

    }

    private static Properties readProperties(final String path) throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcPersonDaoTest.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }
}