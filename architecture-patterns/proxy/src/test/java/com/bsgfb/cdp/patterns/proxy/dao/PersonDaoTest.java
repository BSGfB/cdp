package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.model.Person;
import com.bsgfb.cdp.patterns.proxy.util.DatabaseUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class PersonDaoTest {

    private PersonDao personDao;

    @Before
    public void init() throws IOException, ClassNotFoundException, SQLException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(this.getClass().getClassLoader().getResourceAsStream("database.properties"));

        Properties queries = new Properties();
        queries.load(this.getClass().getClassLoader().getResourceAsStream("queries.properties"));

        DataSource dataSource = DatabaseUtil.createDataSource(databaseProperties);

        DatabaseUtil.populateDatabase(dataSource, queries);

        personDao = new JdbcPersonDao(queries, dataSource);
    }

    @Test
    public void readPerson() throws SQLException {
        Person bob = personDao.readPerson("Bob");

        Assert.assertEquals("Bob", bob.getUsername());
        Assert.assertEquals("123", bob.getPassword());
    }
}