package com.bsgfb.cdp.race.runner;

import com.bsgfb.cdp.race.model.Car;
import com.bsgfb.cdp.race.model.Race;
import com.bsgfb.cdp.race.util.ThreadUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Start {
    private final static Logger logger = LogManager.getLogger(Start.class);
    public static final String DISQUALIFIED_PILOT_NAME = "Jonah";
    public static final long WAIT_TIME = 5000L;

    /**
     * Main method to star race
     * <p>
     * Execution flow:
     * 1. Create cars for racing
     * 2. Create an thread pool. The pool size is the number of cars
     * 3. Create queue to store cars finish position
     * 4. Run each car in separate thread
     * 5. Wait for the race to end
     * 6. Show first car
     *
     * @param args from outside
     */
    public static void main(String[] args) {
        Car[] cars = carFactory();
        ExecutorService service = Executors.newFixedThreadPool(cars.length);

        Queue<Car> top = new ConcurrentLinkedQueue<>();

        CompletableFuture[] completableFutures = Stream.of(cars)
                .parallel()
                .map(car -> startRace(car, service))
                .map(future -> future.thenApplyAsync(top::add))
                .toArray(CompletableFuture[]::new);

        try {
            CompletableFuture.allOf(completableFutures).get();
        } catch (ExecutionException ignored) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug("The first one is [" + top.element().getPilotName() + "]");
        service.shutdown();
    }

    /**
     * Start race for specific car. If car pilot has DISQUALIFIED_PILOT_NAME,
     * then this car will be disqualify after a specific period of time
     *
     * @param car     for race
     * @param service ExecutorService to run car
     * @return CompletableFuture with running car
     */
    static CompletableFuture<Car> startRace(final Car car, final ExecutorService service) {
        CompletableFuture<Car> carCompletableFuture = new CompletableFuture<>();
        Future<?> submit = service.submit(new Race(car, carCompletableFuture));

        if (DISQUALIFIED_PILOT_NAME.equals(car.getPilotName()))
            disqualify(submit, carCompletableFuture, WAIT_TIME);

        return carCompletableFuture;
    }

    /**
     * Stops thread execution after a small period of time
     *
     * @param future               of current running thread
     * @param carCompletableFuture async item which work with current thread
     */
    static void disqualify(Future future, CompletableFuture<Car> carCompletableFuture, final Long waitTime) {
        CompletableFuture
                .runAsync(() -> ThreadUtil.sleep(waitTime))
                .thenRunAsync(() -> {
                    future.cancel(true);
                    carCompletableFuture.cancel(true);
                });
    }

    /**
     * Create cars for race
     *
     * @return array of cars
     */
    static Car[] carFactory() {
        return new Car[]{
                new Car("Bob", 1100L),
                new Car("Flash", 1500L),
                new Car("Slowpoke", 1300L),
                new Car(DISQUALIFIED_PILOT_NAME, 1200L)
        };
    }
}
