package com.bsgfb.cdp.deadlock.runner;

import com.bsgfb.cdp.deadlock.model.NonBlockingRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Start {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Object lock1 = "lock1";
        Object lock2 = "lock2";
        Object lock3 = "lock3";

        Stream.of(
                new NonBlockingRunner(lock1, lock2),
                new NonBlockingRunner(lock2, lock3),
                new NonBlockingRunner(lock3, lock1)
        ).forEach(service::submit);

        service.shutdown();
    }
}
