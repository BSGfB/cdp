package com.bsgfb.cdp.classloader.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RussianLanguageModule implements LanguageModule {
    private final static Logger logger = LogManager.getLogger(RussianLanguageModule.class);
    private static final String MESSAGE = "Привет, мир!";

    @Override
    public String sayHelloWorld() {
        logger.debug(MESSAGE);
        return MESSAGE;
    }
}
