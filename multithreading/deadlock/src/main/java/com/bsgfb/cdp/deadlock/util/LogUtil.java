package com.bsgfb.cdp.deadlock.util;

public class LogUtil {
    public static String createLogTryLock(Object lock) {
        return "Thread name is [" + Thread.currentThread().getName() + "] Try to lock on [" + lock + "]";
    }

    public static String createLogLocked(Object lock) {
        return "Thread name is [" + Thread.currentThread().getName() + "] Locked on [" + lock + "]";
    }

    public static String createLogReleaseLock(Object lock) {
        return "Thread name is [" + Thread.currentThread().getName() + "] Release [" + lock + "]";
    }

    public static String createLogEndMethod() {
        return "Thread name is [" + Thread.currentThread().getName() + "] Reached the end";
    }
}
