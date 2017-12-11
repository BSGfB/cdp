package com.bsgfb.cdp.todo.runner;

import com.bsgfb.cdp.todo.client.ConsoleClient;
import com.bsgfb.cdp.todo.dao.InMemoryTodoListDao;
import com.bsgfb.cdp.todo.service.TodoServiceImpl;
import com.bsgfb.cdp.todo.util.FileHelper;
import com.bsgfb.cdp.todo.util.JsonFileHelper;
import com.bsgfb.cdp.todo.util.ScannerConsoleInput;

public class Start {
    public static void main(String[] args) {
        ScannerConsoleInput scannerConsoleInput = new ScannerConsoleInput(System.in);
        TodoServiceImpl todoService = new TodoServiceImpl(new InMemoryTodoListDao());
        FileHelper fileHelper = new JsonFileHelper();

        new ConsoleClient(todoService, scannerConsoleInput, fileHelper).run();
    }
}
