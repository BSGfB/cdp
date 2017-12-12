package com.bsgfb.cdp.todo.util;

import com.bsgfb.cdp.todo.model.Todo;

import java.io.IOException;
import java.util.List;

/**
 * FileHelper is wrapper to systematize work with save and read operations
 */
public interface FileHelper {
    /**
     * Save list of todos to file by path
     * @param path full or relative path to file
     * @param todos list of todos
     * @throws IOException if something goes wrong
     */
    void toFile(String path, List<Todo> todos) throws IOException;

    /**
     * Read and return list of todo items from file by path
     * @param path full or relative path to file
     * @return list of todo items
     * @throws IOException if something goes wrong
     */
    List<Todo> fromFile(String path) throws IOException;
}
