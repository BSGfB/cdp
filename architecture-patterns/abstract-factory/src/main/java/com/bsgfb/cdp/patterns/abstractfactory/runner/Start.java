package com.bsgfb.cdp.patterns.abstractfactory.runner;

import com.bsgfb.cdp.patterns.abstractfactory.client.ConsoleClient;
import com.bsgfb.cdp.patterns.abstractfactory.model.FactoryConfiguration;
import com.bsgfb.cdp.patterns.abstractfactory.model.PersonDaoFactory;
import com.bsgfb.cdp.patterns.abstractfactory.util.ScannerInput;

import java.io.IOException;
import java.sql.SQLException;

public class Start {
    public static void main(String[] args) throws IOException, SQLException {
        FactoryConfiguration configuration = FactoryConfiguration.builder()
                .backupTime(5)
                .fileLocation(System.getProperty("cdp.file", ""))
                .build();

        PersonDaoFactory personDaoFactory = PersonDaoFactory.getFactory(configuration);

        ScannerInput scannerInput = new ScannerInput(System.in);

        new ConsoleClient(scannerInput, personDaoFactory.createPersonDao()).run();
    }
}
