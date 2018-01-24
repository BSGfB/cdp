package com.bsgfb.cdp.patterns.abstractfactory.client;

import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;
import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.UserInput;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

/**
 * ConsoleClient is used to work with PersonDao.
 *
 * Constructor parameters:
 * 1. userInput is abstraction on user input way
 * 2. personDao class to work with person collation (database, file)
 */
@AllArgsConstructor
public class ConsoleClient {
    private static final Logger LOGGER = LogManager.getLogger(ConsoleClient.class);

    private UserInput userInput;
    private PersonDao personDao;

    /**
     * Run application flow.
     * 1. Show menu
     * 2. Select item
     * 3. Performs operation according selected item
     */
    public void run() {
        while (true) {
            MenuItem item = selectMenuItem();

            switch (item) {
                case EXIT:
                    LOGGER.debug("Goodbye");
                    return;
                case READ_ALL:
                    personDao.readPeople().subscribe(LOGGER::debug);
                    break;
                case READ_ONE:
                    personDao.readPerson(readValue("Name:")).subscribe((Consumer<Person>) LOGGER::debug);
                    break;
                case SAVE_ONE:
                    personDao.writePerson(Single.just(
                            Person
                                    .builder()
                                    .username(readValue("Name:"))
                                    .password(readValue("Password:"))
                                    .build()));
                    break;
            }
        }

    }

    /**
     * Aggregate couple operation to one method. It's more human readable
     * @param message to show in console
     * @return value from userInput (String)
     */
    private String readValue(String message) {
        LOGGER.debug("Person name: ");
        return userInput.readString();
    }

    /**
     * Show all MenuItem (except: UNDEFINED) and return selected menu item
     * @return selected menu item
     */
    private MenuItem selectMenuItem() {
        LOGGER.debug("Menu:");
        Stream.of(MenuItem.values()).filter(v -> v != MenuItem.UNDEFINED).forEachOrdered(LOGGER::debug);

        while (true) {
            MenuItem item = MenuItem.getInstance(userInput.nextInt());

            if (item != MenuItem.UNDEFINED)
                return item;
        }
    }

    /**
     * Aggregate all menu operations
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum MenuItem {
        READ_ALL("Show all", 1),
        READ_ONE("Show one", 2),
        SAVE_ONE("Save one", 3),
        EXIT("Exit", 0),
        UNDEFINED("Undefined", -1);


        private String prettyName;
        private Integer index;

        public static MenuItem getInstance(final Integer value) {
            return Stream.of(values()).filter(v -> v.index.equals(value)).findFirst().orElse(UNDEFINED);
        }

        public Integer getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "[" + index + "]. " + prettyName;
        }
    }
}
