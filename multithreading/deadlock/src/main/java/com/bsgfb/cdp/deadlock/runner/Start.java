package com.bsgfb.cdp.deadlock.runner;

import com.bsgfb.cdp.deadlock.model.NonBlockingRunner;
import com.bsgfb.cdp.deadlock.model.Waitress;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Non blocking runner
 */
public class Start {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        Waitress waitress = new Waitress(3);
        waitress.setDaemon(true);
        waitress.start();

        Stream.of(
                new NonBlockingRunner(waitress),
                new NonBlockingRunner(waitress),
                new NonBlockingRunner(waitress)
        ).forEach(service::submit);

        service.shutdown();
    }
}
