package com.bsgfb.cdp.classloader.runner;

import com.bsgfb.cdp.classloader.client.ConsoleClient;

import java.io.IOException;
import java.util.Scanner;

public class Start {
    private static final String MODULE_DIR = "classloader/loader/src/main/resources/";

    public static void main(String[] args) throws IOException {
        new ConsoleClient(new Scanner(System.in), MODULE_DIR).run();
    }
}
