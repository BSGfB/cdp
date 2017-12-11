package com.bsgfb.cdp.todo.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ScannerConsoleInputTest {

    @Test
    public void readNumber() {
        String expectedString = "Say hello 5\n";
        assertEquals(5, new ScannerConsoleInput(new ByteArrayInputStream(expectedString.getBytes())).readNumber());
    }

    @Test
    public void readString() {
        String expectedString = "Say hello";
        assertEquals(expectedString, new ScannerConsoleInput(new ByteArrayInputStream(expectedString.getBytes())).readString());
    }
}