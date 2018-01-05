package com.bsgfb.cdp.deadlock.runner;

import com.bsgfb.cdp.deadlock.model.BlockingRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Deadlock between three threads
 */
public class DeadlockStart {


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Object lock1 = "lock1";
        Object lock2 = "lock2";
        Object lock3 = "lock3";

        Stream.of(
                new BlockingRunner(lock1, lock2),
                new BlockingRunner(lock2, lock3),
                new BlockingRunner(lock3, lock1)
        ).forEach(service::submit);

        service.shutdown();
    }
}
