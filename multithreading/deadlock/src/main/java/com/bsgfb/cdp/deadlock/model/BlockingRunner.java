package com.bsgfb.cdp.deadlock.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.bsgfb.cdp.deadlock.util.LogUtil.*;

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
            TimeUnit.MILLISECONDS.sleep((long) 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
