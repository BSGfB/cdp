package com.bsgfb.cdp.patterns.abstractfactory.util;

/**
 * UserInput is wrapper to systematize user input, and exclude variety of way to interaction
 */
public interface UserInput {

    /**
     * Read specific number
     * @return one number type int
     */
    int nextInt();

    /**
     * Read one string
     * @return one string
     */
    String readString();
}
