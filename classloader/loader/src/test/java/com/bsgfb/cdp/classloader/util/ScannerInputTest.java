package com.bsgfb.cdp.classloader.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class ScannerInputTest {

    @Test
    public void nextInt() {
        String expectedString = "0\n";
        assertEquals(0, new ScannerInput(new ByteArrayInputStream(expectedString.getBytes())).nextInt());
    }

    @Test
    public void nextIntWithBadInput() {
        String expectedString = "test 5";
        assertEquals(5, new ScannerInput(new ByteArrayInputStream(expectedString.getBytes())).nextInt());
    }
}