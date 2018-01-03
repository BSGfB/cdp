package com.bsgfb.cdp.gc.runner;

public class StackOverflowStart {

    public static void main(String[] args) {
        getNextNumber(0);
    }

    public static long getNextNumber(long n) {
        return getNextNumber(++n);
    }
}
