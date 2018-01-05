package com.bsgfb.cdp.deadlock.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.bsgfb.cdp.deadlock.util.LogUtil.*;

/**
 * Run thread which has code to block current thread
 * <p>
 * Blocking happens when more than one thread started at the same time,
 * try to lock1 and lock2 in different order. Blocking is possible,
 * because there are two nested synchronized block
 */
public class BlockingRunner implements Runnable {
    private final static Logger logger = LogManager.getLogger(BlockingRunner.class);

    private final Object lock1;
    private final Object lock2;

    public BlockingRunner(final Object lock1, final Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        logger.debug(createLogTryLock(lock1));
        synchronized (lock1) {
            logger.debug(createLogLocked(lock1));

            work();

            logger.debug(createLogTryLock(lock2));
            synchronized (lock2) {
                logger.debug(createLogLocked(lock2));

                work();

                logger.debug(createLogReleaseLock(lock2));
            }

            logger.debug(createLogReleaseLock(lock1));
        }

        logger.debug(createLogEndMethod());
    }

    private void work() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
