package com.bsgfb.cdp.patterns.abstractfactory.util;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;

import java.io.IOException;
import java.util.List;

/**
 * FileHelper is wrapper to systematize work with save and read operations
 */
public interface FileHelper {
    /**
     * Save list of todos to file by path
     *
     * @param path   full or relative path to file
     * @param people will be saved to file
     * @throws IOException if something goes wrong
     */
    void toFile(String path, List<Person> people) throws IOException;

    /**
     * Read and return person list
     *
     * @param path full or relative path to file
     * @return person list
     * @throws IOException if something goes wrong
     */
    List<Person> fromFile(String path) throws IOException;
}
