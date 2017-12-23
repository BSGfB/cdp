package com.bsgfb.cdp.classloader.runner;

import com.bsgfb.cdp.classloader.client.ConsoleClient;
import com.bsgfb.cdp.classloader.util.ScannerInput;

import java.io.IOException;

public class Start {
    private static final String MODULE_DIR = "classloader/loader/src/main/resources/";

    public static void main(String[] args) throws IOException {
        new ConsoleClient(new ScannerInput(System.in), MODULE_DIR).run();
    }
}
