package com.bsgfb.cdp.patterns.abstractfactory.client;

import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;
import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.UserInput;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class ConsoleClientTest {
    public static final String BOB_NAME = "Bob";
    public static final Single<Person> BOB = Single.just(Person.builder().id(1L).username(BOB_NAME).password("123").build());
    public static final String BOB_PASSWORD = "123";

    private UserInput userInput;
    private PersonDao personDao;
    private ConsoleClient consoleClient;

    @Before
    public void init() {
        userInput = EasyMock.createNiceMock(UserInput.class);
        personDao = EasyMock.createNiceMock(PersonDao.class);
        consoleClient = new ConsoleClient(userInput, personDao);
    }

    @After
    public void cleanUp() throws InterruptedException {
        replay(userInput, personDao);

        consoleClient.run();

        verify(userInput);
        verify(personDao);
    }

    @Test
    public void getOnePersonTest() {
        expect(userInput.nextInt()).andReturn(ConsoleClient.MenuItem.READ_ONE.getIndex());
        expect(userInput.readString()).andReturn(BOB_NAME);
        expect(personDao.readPerson(BOB_NAME)).andReturn(BOB);
    }

    @Test
    public void getPeopleTest() {
        expect(userInput.nextInt()).andReturn(ConsoleClient.MenuItem.READ_ALL.getIndex());
        expect(personDao.readPeople()).andReturn(Observable.just(BOB.blockingGet()));
    }

    @Test
    public void saveOnePersonTest() {
        Single<Person> justPerson = Single.just(Person.builder().username(BOB_NAME).password(BOB_PASSWORD).build());
        expect(userInput.nextInt()).andReturn(ConsoleClient.MenuItem.SAVE_ONE.getIndex());
        expect(userInput.readString()).andReturn(BOB_NAME).andReturn(BOB_PASSWORD);

        personDao.writePerson(justPerson);

        expectLastCall().anyTimes();
    }

    @Test
    public void exitTest() {
        expect(userInput.nextInt()).andReturn(ConsoleClient.MenuItem.EXIT.getIndex());
    }
}