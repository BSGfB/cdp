package com.bsgfb.cdp.deadlock.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * The class goal to synchronize work of couple threads
 * <p>
 * The class idea based on dining philosophers problem.
 * To problem solved with using extra thread to manage threads work.
 * There are two semaphore.
 * First one is used to control number of active threads.
 * The second is used to condole number of forks.
 */
public class Waitress extends Thread {
    private final static Logger logger = LogManager.getLogger(BlockingRunner.class);

    private Semaphore seats;
    private Semaphore forks;

    public Waitress(final Integer numberOfPhilosophers) {
        this.forks = new Semaphore(numberOfPhilosophers);
        this.seats = new Semaphore(numberOfPhilosophers - 1);
    }

    @Override
    public void run() {
        logger.debug("thread [Waitress] is running");
    }

    public void takeSeat() throws InterruptedException {
        seats.acquire();
    }

    public void releaseSeat() {
        seats.release();
    }

    public void getFork() throws InterruptedException {
        forks.acquire();
    }

    public void releaseFork() {
        forks.release();
    }
}
