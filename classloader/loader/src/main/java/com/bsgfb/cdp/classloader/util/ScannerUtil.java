package com.bsgfb.cdp.classloader.util;

import java.util.Scanner;

/**
 * This class is wrapper around Scanner methods
 */
public class ScannerUtil {

    /**
     * Reads first input value from input stream, other values will be skipped
     *
     * @param scanner tool to read from stream
     * @return int value from input stream
     */
    public static int nextInt(Scanner scanner) {
        while (scanner.hasNext() && !scanner.hasNextInt())
            scanner.next();

        int i = scanner.nextInt();
        if (scanner.hasNextLine())
            scanner.nextLine();

        return i;
    }
}
