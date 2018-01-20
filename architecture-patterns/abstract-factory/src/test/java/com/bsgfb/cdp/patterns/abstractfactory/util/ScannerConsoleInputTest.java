package com.bsgfb.cdp.patterns.abstractfactory.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class ScannerConsoleInputTest {

    @Test
    public void readNumber() {
        String expectedString = "Say hello 5\n";
        assertEquals(5, new ScannerInput(new ByteArrayInputStream(expectedString.getBytes())).nextInt());
    }

    @Test
    public void readString() {
        String expectedString = "Say hello";
        assertEquals(expectedString, new ScannerInput(new ByteArrayInputStream(expectedString.getBytes())).readString());
    }
}