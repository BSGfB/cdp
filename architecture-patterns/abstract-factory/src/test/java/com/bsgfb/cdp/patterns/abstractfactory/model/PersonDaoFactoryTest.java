package com.bsgfb.cdp.patterns.abstractfactory.model;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class PersonDaoFactoryTest {

    @Test
    public void getJdbcFactory() throws IOException, SQLException {
        FactoryConfiguration configuration = FactoryConfiguration.builder()
                .fileLocation("")
                .dbProperties("database2.properties")
                .build();

        PersonDaoFactory factory = PersonDaoFactory.getFactory(configuration);

        assertTrue(factory instanceof JdbcPersonDaoFactory);
    }

    @Test
    public void getFileFactory() throws IOException, SQLException {
        FactoryConfiguration configuration = FactoryConfiguration.builder().fileLocation("file.json").build();
        PersonDaoFactory factory = PersonDaoFactory.getFactory(configuration);

        assertTrue(factory instanceof FilePersonDaoFactory);
    }
}