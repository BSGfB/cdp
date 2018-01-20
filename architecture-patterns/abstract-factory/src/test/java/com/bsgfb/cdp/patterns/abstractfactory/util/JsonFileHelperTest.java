package com.bsgfb.cdp.patterns.abstractfactory.util;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class JsonFileHelperTest {

    public static final Person EXPECTED_TODO = Person.builder().username("Bob").password("123").build();
    public static final String FILE_TO_FILE_JSON = "test.json";

    @Test
    public void toFile() throws IOException {
        new JsonFileHelper().toFile(FILE_TO_FILE_JSON, Collections.singletonList(EXPECTED_TODO));
        Person person = new JsonFileHelper().fromFile(FILE_TO_FILE_JSON).stream().findFirst().orElse(null);
        Assert.assertEquals(EXPECTED_TODO, person);
    }

    @Test
    public void fromFile() throws IOException {
        new JsonFileHelper().toFile(FILE_TO_FILE_JSON, Collections.singletonList(EXPECTED_TODO));
        Person person = new JsonFileHelper().fromFile(FILE_TO_FILE_JSON).stream().findFirst().orElse(null);
        Assert.assertEquals(EXPECTED_TODO, person);
    }

    @Test(expected = IOException.class)
    public void fromWrongFile() throws IOException {
        new JsonFileHelper().toFile("wrong/file/exeptional!!!!!!", Collections.singletonList(EXPECTED_TODO));
    }

    public void toWrongFile() throws IOException {
        assertTrue(new JsonFileHelper().fromFile("wrong/file/exeptional!!!!!!").isEmpty());
    }
}