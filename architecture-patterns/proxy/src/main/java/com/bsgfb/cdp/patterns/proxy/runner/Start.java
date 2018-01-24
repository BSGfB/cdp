package com.bsgfb.cdp.patterns.proxy.runner;

import com.bsgfb.cdp.patterns.proxy.dao.CashePersonDaoProxy;
import com.bsgfb.cdp.patterns.proxy.dao.PersonDao;
import com.bsgfb.cdp.patterns.proxy.model.Person;
import com.bsgfb.cdp.patterns.proxy.util.DatabaseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Start {
    private static final Logger LOGGER = LogManager.getLogger(Start.class);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        LOGGER.debug("Application started...");

        Properties databaseProperties = new Properties();
        databaseProperties.load(Start.class.getClassLoader().getResourceAsStream("database.properties"));

        Properties queries = new Properties();
        queries.load(Start.class.getClassLoader().getResourceAsStream("queries.properties"));

        DataSource dataSource = DatabaseUtil.createDataSource(databaseProperties);
        DatabaseUtil.populateDatabase(dataSource, queries);

        PersonDao personDao = new CashePersonDaoProxy(dataSource, queries);


        while (true) {
            Person person = personDao.readPerson(RANDOM.nextInt(2) == 0 ? "Siarhei" : "Bob");
            LOGGER.debug("Loaded person is [{}]", person);

            LOGGER.debug("To get next person press [ENTER] or write exit to exit");
            String nextLine = SCANNER.nextLine();
            if ("exit".equals(nextLine))
                return;
        }
    }
}
