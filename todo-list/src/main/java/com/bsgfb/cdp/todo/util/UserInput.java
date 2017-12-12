package com.bsgfb.cdp.todo.util;

/**
 * UserInput is wrapper to systematize user input, and exclude variety of way to interaction
 */
public interface UserInput {
    /**
     * Read specific number
     * @return one number type int
     */
    int readNumber();

    /**
     * Read one string
     * @return one string
     */
    String readString();
}
