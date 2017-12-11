package com.bsgfb.cdp.todo.util;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class JsonFileHelperTest {

    public static final Todo EXPECTED_TODO = new Todo(1L, "Say hello", TodoStatus.IN_PROGRESS);
    public static final String FILE_TO_FILE_JSON = "test.json";

    @Test
    public void toFile() throws IOException {
        new JsonFileHelper().toFile(FILE_TO_FILE_JSON, Collections.singletonList(EXPECTED_TODO));
        Assert.assertEquals(EXPECTED_TODO, new JsonFileHelper().fromFile(FILE_TO_FILE_JSON).stream().findFirst().orElse(null));
    }

    @Test
    public void fromFile() throws IOException {
        new JsonFileHelper().toFile(FILE_TO_FILE_JSON, Collections.singletonList(EXPECTED_TODO));
        Assert.assertEquals(EXPECTED_TODO, new JsonFileHelper().fromFile(FILE_TO_FILE_JSON).stream().findFirst().orElse(null));
    }

    @Test(expected = IOException.class)
    public void fromWrongFile() throws IOException {
        new JsonFileHelper().toFile("wrong/file/exeptional!!!!!!", Collections.singletonList(EXPECTED_TODO));
    }

    @Test(expected = IOException.class)
    public void toWrongFile() throws IOException {
        new JsonFileHelper().fromFile("wrong/file/exeptional!!!!!!");
    }
}