package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.FileHelper;
import io.reactivex.Single;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

public class FilePersonDaoTest {

    public static final String TEST_TXT = "Test.txt";
    public static final Person INSERTED_PERSON = Person.builder().username("Bob").password("123").build();

    FileHelper fileHelper;
    PersonDao personDao;

    @Before
    public void init() throws IOException {
        fileHelper = EasyMock.createNiceMock(FileHelper.class);
        List<Person> people = new ArrayList<>();
        people.add(INSERTED_PERSON);

        expect(fileHelper.fromFile(TEST_TXT)).andReturn(people);
        expectLastCall().anyTimes();

        replay(fileHelper);

        personDao = new FilePersonDao(TEST_TXT, fileHelper, 1);
    }

    @After
    public void cleanUp() {
        verify(fileHelper);
    }

    @Test
    public void writePerson() throws IOException {
        Person personToWrite = Person.builder().username("Siarhei").password("qwe").build();
        personDao.writePerson(Single.just(personToWrite));

        Person actualPerson = personDao.readPerson(personToWrite.getUsername()).blockingGet();

        Assert.assertEquals(personToWrite.getUsername(), actualPerson.getUsername());
        Assert.assertEquals(personToWrite.getPassword(), actualPerson.getPassword());
    }

    @Test
    public void readPeople() {
        Person actualPerson = personDao.readPerson(INSERTED_PERSON.getUsername()).blockingGet();

        Assert.assertEquals(INSERTED_PERSON.getUsername(), actualPerson.getUsername());
        Assert.assertEquals(INSERTED_PERSON.getPassword(), actualPerson.getPassword());

    }

    @Test
    public void readPerson() {
        Person actualPerson = personDao.readPeople().toList().blockingGet().get(0);

        Assert.assertEquals(INSERTED_PERSON.getUsername(), actualPerson.getUsername());
        Assert.assertEquals(INSERTED_PERSON.getPassword(), actualPerson.getPassword());
    }
}