package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.model.Todo;

import java.util.List;

public interface TodoListService {
    List<Todo> findAll();

    Long save(Todo todo);

    void remove(Long id);

    void removeAll();

    Todo findById(Long id);
}