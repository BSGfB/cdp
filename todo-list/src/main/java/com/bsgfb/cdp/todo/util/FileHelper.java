package com.bsgfb.cdp.todo.util;

import com.bsgfb.cdp.todo.model.Todo;

import java.io.IOException;
import java.util.List;

public interface FileHelper {
    void toFile(String path, List<Todo> todos) throws IOException;
    List<Todo> fromFile(String path) throws IOException;
}
