package com.bsgfb.cdp.deadlock.util;

public class LogUtil {
    public static String createLogTryLock(Object lock) {
        return "Thread [" + Thread.currentThread().getName() + "] tries to lock on [" + lock + "]";
    }

    public static String createLogLocked(Object lock) {
        return "Thread [" + Thread.currentThread().getName() + "] has locked on [" + lock + "]";
    }

    public static String createLogReleaseLock(Object lock) {
        return "Thread [" + Thread.currentThread().getName() + "] has released [" + lock + "]";
    }

    public static String createLogEndMethod() {
        return "Thread [" + Thread.currentThread().getName() + "] has reached the end";
    }

    public static String createLogTryTakeSeat() {
        return "Thread [" + Thread.currentThread().getName() + "] tries to take a seat";
    }

    public static String createLogTakeSeat() {
        return "Thread [" + Thread.currentThread().getName() + "] has taken a seat";
    }

    public static String createLogReleaseSeat() {
        return "Thread [" + Thread.currentThread().getName() + "] has released a seat";
    }

    public static String createLogTryGetFork(String forkName) {
        return "Thread [" + Thread.currentThread().getName() + "] tries to get a " + forkName + " fork";
    }

    public static String createLogGetFork(String forkName) {
        return "Thread [" + Thread.currentThread().getName() + "] has been gotten a " + forkName + " fork";
    }

    public static String createLogReleaseFork(String forkName) {
        return "Thread [" + Thread.currentThread().getName() + "] has released a " + forkName + " fork";
    }

    public static String createLogStartEat() {
        return "Thread [" + Thread.currentThread().getName() + "] is eating";
    }

    public static String createLogEndEat() {
        return "Thread [" + Thread.currentThread().getName() + "] has finished to eat";
    }
}
