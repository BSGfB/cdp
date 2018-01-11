package com.bsgfb.cdp.race.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Represent race, where LAPS a number of laps.
 */
public class Race implements Runnable {
    private final static Logger logger = LogManager.getLogger(Race.class);
    private static final Integer LAPS = 10;

    private Car car;
    private CompletableFuture<Car> completableFuture;
    private CyclicBarrier cyclicBarrier;

    public Race(final Car car, final CompletableFuture<Car> completableFuture, final CyclicBarrier cyclicBarrier) {
        this.car = car;
        this.completableFuture = completableFuture;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= LAPS; i++) {
            logger.debug("Lap [" + i + " / " + LAPS + "] Pilot name [" + car.getPilotName() + "]");

            try {
                TimeUnit.MILLISECONDS.sleep(car.getBestLapTime());
            } catch (InterruptedException e) {
                return;
            }

            if (Thread.interrupted())
                return;
        }

        completableFuture.complete(car);
    }
}
