package com.bsgfb.cdp.todo.util;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Console implementation of UserInput
 * Read number till you write one. Removes all \n from input stream
 */
public class ScannerConsoleInput implements UserInput {
    Scanner scanner;

    public ScannerConsoleInput(final InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public int readNumber() {
        while (scanner.hasNext() && !scanner.hasNextInt())
            scanner.next();

        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();

            if (scanner.hasNextLine())
                scanner.nextLine();

            return i;
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
