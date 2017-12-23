package com.bsgfb.cdp.classloader.util;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerInput implements UserInput {
    private Scanner scanner;

    public ScannerInput(final InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public int nextInt() {
        while (scanner.hasNext() && !scanner.hasNextInt())
            scanner.next();

        int i = scanner.nextInt();
        if (scanner.hasNextLine())
            scanner.nextLine();

        return i;
    }
}
