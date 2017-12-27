package com.bsgfb.cdp.race.runner;

import com.bsgfb.cdp.race.model.Car;
import com.bsgfb.cdp.race.util.ThreadUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Start {
    private final static Logger logger = LogManager.getLogger(Start.class);

    public static void main(String[] args) {
        Queue<Car> top = new ConcurrentLinkedQueue<>();

        Car[] cars = new Car[]{
                new Car("Bob", 1100L),
                new Car("Flash", 1500L),
                new Car("Slowpoke", 1300L),
                new Car("Jonah", 1200L)
        };

        ExecutorService service = Executors.newFixedThreadPool(cars.length);

        CompletableFuture[] completableFutures = Stream.of(cars).parallel().map(car -> {
            CompletableFuture<Car> carCompletableFuture = new CompletableFuture<>();
            Future<?> submit = service.submit(new com.bsgfb.cdp.race.model.Race(car, carCompletableFuture));
            if (car.getPilotName().equals("Jonah")) {
                CompletableFuture
                        .runAsync(() -> ThreadUtil.sleep(5000))
                        .thenRunAsync(() -> {
                            submit.cancel(true);
                            carCompletableFuture.cancel(true);
                        });
            }

            return carCompletableFuture.thenApplyAsync(top::add);
        }).toArray(CompletableFuture[]::new);

        try {
            CompletableFuture.allOf(completableFutures).get();
        } catch (ExecutionException ignored) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug("The first one is [" + top.element().getPilotName() + "]");
        service.shutdown();
    }
}
