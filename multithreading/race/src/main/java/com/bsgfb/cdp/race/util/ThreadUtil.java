package com.bsgfb.cdp.race.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {
    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
