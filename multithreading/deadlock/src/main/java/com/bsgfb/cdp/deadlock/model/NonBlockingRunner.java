package com.bsgfb.cdp.deadlock.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.bsgfb.cdp.deadlock.util.LogUtil.*;

/**
 * Non blocking Runnable for thread
 * <p>
 * This class asks for permission to Waitress.
 * After class gets all permissions, it executes method work()
 * When all work is done, class release all permissions
 */
public class NonBlockingRunner implements Runnable {
    private final static Logger logger = LogManager.getLogger(NonBlockingRunner.class);

    private Waitress waitress;

    public NonBlockingRunner(final Waitress waitress) {
        this.waitress = waitress;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                logger.debug(createLogTryTakeSeat());
                waitress.takeSeat();
                logger.debug(createLogTakeSeat());

                logger.debug(createLogTryGetFork("left"));
                waitress.getFork();
                logger.debug(createLogGetFork("left"));

                logger.debug(createLogTryGetFork("right"));
                waitress.getFork();
                logger.debug(createLogGetFork("right"));

                logger.debug(createLogStartEat());
                work();
                logger.debug(createLogEndEat());

                waitress.releaseFork();
                logger.debug(createLogReleaseFork("left"));

                waitress.releaseFork();
                logger.debug(createLogReleaseFork("right"));

                waitress.releaseSeat();
                logger.debug(createLogReleaseSeat());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug(createLogEndMethod());
    }


    private void work() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
