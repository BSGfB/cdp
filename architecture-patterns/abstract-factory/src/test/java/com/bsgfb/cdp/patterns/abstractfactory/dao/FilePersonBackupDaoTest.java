package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.FileHelper;
import org.easymock.EasyMock;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

public class FilePersonBackupDaoTest {
    public static final String TEST_TXT = "Test.txt";

    @Test
    public void backupTest() throws IOException {
        FileHelper fileHelper = EasyMock.createNiceMock(FileHelper.class);
        List<Person> people = new ArrayList<>();
        people.add(Person.builder().username("Bob").password("123").build());

        expect(fileHelper.fromFile(TEST_TXT)).andReturn(people);
        expectLastCall().times(3, 5);

        replay(fileHelper);

        new FilePersonDao(TEST_TXT, fileHelper, 1);
    }
}
