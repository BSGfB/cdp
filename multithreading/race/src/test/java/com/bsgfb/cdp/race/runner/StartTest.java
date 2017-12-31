package com.bsgfb.cdp.race.runner;

import com.bsgfb.cdp.race.model.Car;
import com.bsgfb.cdp.race.model.Race;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class StartTest {

    private Car car;
    private ExecutorService service;

    @Before
    public void init() {
        car = new Car("test", 10L);
        service = Executors.newFixedThreadPool(1);
    }

    @Test
    public void startRace() throws ExecutionException, InterruptedException {
        CompletableFuture<Car> carCompletableFuture = Start.startRace(car, service);
        carCompletableFuture.get();
        assertTrue(carCompletableFuture.isDone());
    }

    @Test(expected = CancellationException.class)
    public void disqualify() throws ExecutionException, InterruptedException {
        CompletableFuture<Car> carCompletableFuture = new CompletableFuture<>();
        Future<?> submit = service.submit(new Race(car, carCompletableFuture));
        Start.disqualify(submit, carCompletableFuture, 1L);

        carCompletableFuture.get();
    }
}