package com.bsgfb.cdp.race.util;

import java.util.concurrent.TimeUnit;

/**
 * The goal of class create nice wrapped around some methods
 */
public class ThreadUtil {

    /**
     * Wrapper around thread sleep. This method ignored all exceptions
     * @param milliseconds needs to sleep
     */
    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
