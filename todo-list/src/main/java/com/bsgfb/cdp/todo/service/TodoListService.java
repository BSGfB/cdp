package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;

import java.util.List;

public interface TodoListService {
    List<Todo> findAll();

    Long save(Todo todo);

    void remove(Long id);

    void removeAll();

    Todo findById(Long id);

    void updateTodoStatusById(Long id, TodoStatus todoStatus);
}