package com.bsgfb.cdp.classloader.client;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class BadConsoleClientTest {
    private static final String WRONG_MODULE_DIR = "wrong/path/";

    @Test(expected = NoSuchFileException.class)
    public void wrongPath() throws IOException {
        new ConsoleClient(null, WRONG_MODULE_DIR);
    }
}
