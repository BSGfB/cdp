package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.model.Person;
import com.bsgfb.cdp.patterns.proxy.util.DatabaseUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class PersonDaoTest {

    private static DataSource DATA_SOURCE;

    private PersonDao personDao;
    private PersonDao cachePersonDabProxy;

    @BeforeClass
    public static void init() throws IOException, ClassNotFoundException, SQLException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(PersonDaoTest.class.getClassLoader().getResourceAsStream("database.properties"));

        Properties queries = new Properties();
        queries.load(PersonDaoTest.class.getClassLoader().getResourceAsStream("queries.properties"));

        DATA_SOURCE = DatabaseUtil.createDataSource(databaseProperties);
        DatabaseUtil.populateDatabase(DATA_SOURCE, queries);


    }

    @Before
    public void initDao() throws IOException {
        Properties queries = new Properties();
        queries.load(this.getClass().getClassLoader().getResourceAsStream("queries.properties"));

        personDao = new JdbcPersonDao(queries, DATA_SOURCE);
        cachePersonDabProxy = new CashePersonDaoProxy(DATA_SOURCE, queries);
    }

    @Test
    public void readPerson() throws SQLException {
        Person bob = personDao.readPerson("Bob");

        Assert.assertEquals("Bob", bob.getUsername());
        Assert.assertEquals("123", bob.getPassword());
    }

    @Test
    public void readPersonProxy() throws SQLException {
        Person bob1 = cachePersonDabProxy.readPerson("Bob");
        Person bob2 = cachePersonDabProxy.readPerson("Bob");
        Person bob3 = cachePersonDabProxy.readPerson("Bob");
        Person siarhei = cachePersonDabProxy.readPerson("Siarhei");
        Person siarhei2 = cachePersonDabProxy.readPerson("Siarhei");
    }
}