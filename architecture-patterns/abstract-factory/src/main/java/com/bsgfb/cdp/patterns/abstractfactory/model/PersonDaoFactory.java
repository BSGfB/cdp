package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static com.bsgfb.cdp.patterns.abstractfactory.util.JdbcUtil.populateDatabase;

public abstract class PersonDaoFactory {

    /**
     * Create new PersonDaoFactory based on FactoryConfiguration
     *
     * Selection rules:
     * If factoryConfiguration.getFileLocation() is present, them FilePersonDaoFactory
     * otherwise JdbcPersonDaoFactory will be created
     *
     * @param factoryConfiguration is used to create appropriate PersonDaoFactory
     * @return PersonDaoFactory according FactoryConfiguration
     * @throws IOException if io exception occur
     * @throws SQLException if sql exception occur
     */
    public static PersonDaoFactory getFactory(final FactoryConfiguration factoryConfiguration) throws IOException, SQLException {
        if ("".equals(factoryConfiguration.getFileLocation())) {
            Properties databaseProperties = readProperties(factoryConfiguration.getDbProperties());
            Properties queries = readProperties(factoryConfiguration.getDbQueries());
            DataSource dataSource = factoryConfiguration.getDataSourceFactory().createDataSource(databaseProperties);

            populateDatabase(dataSource, queries);

            return new JdbcPersonDaoFactory(dataSource, queries);
        } else {
            return new FilePersonDaoFactory(factoryConfiguration.getFileLocation(), factoryConfiguration.getBackupTime());
        }
    }

    public abstract PersonDao createPersonDao();

    private static Properties readProperties(final String path) throws IOException {
        Properties properties = new Properties();
        properties.load(PersonDaoFactory.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }
}
