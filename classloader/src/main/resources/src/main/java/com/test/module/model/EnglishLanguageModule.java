package com.test.module.model;

import com.bsgfb.cdp.classloader.model.LanguageModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnglishLanguageModule implements LanguageModule {
    private final static Logger logger = LogManager.getLogger(EnglishLanguageModule.class);
    private static final String MESSAGE = "Hello, World!";

    @Override
    public String sayHelloWorld() {
        logger.debug(MESSAGE);
        return MESSAGE;
    }
}
