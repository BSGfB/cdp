package com.bsgfb.cdp.race.model;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class RaceTest {

    private Car car;
    private CompletableFuture<Car> carCompletableFuture;

    @Before
    public void init() {
        car = new Car("test", 10L);
        carCompletableFuture = new CompletableFuture<>();
    }

    @Test
    public void run() {
        new Race(car, carCompletableFuture).run();

        assertTrue(carCompletableFuture.isDone());
    }

    @Test(expected = ExecutionException.class)
    public void runWithStop() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(new Race(car, carCompletableFuture)).cancel(true);
        carCompletableFuture.cancel(true);

        CompletableFuture.allOf(carCompletableFuture).get();
    }
}