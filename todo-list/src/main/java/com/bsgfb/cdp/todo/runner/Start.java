package com.bsgfb.cdp.todo.runner;

import com.bsgfb.cdp.todo.client.ConsoleClient;
import com.bsgfb.cdp.todo.dao.InMemoryTodoListDao;
import com.bsgfb.cdp.todo.service.TodoServiceImpl;

public class Start {
    public static void main(String[] args) {
        new ConsoleClient(new TodoServiceImpl(new InMemoryTodoListDao())).run();
    }
}
