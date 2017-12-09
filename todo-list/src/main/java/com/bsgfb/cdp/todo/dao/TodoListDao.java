package com.bsgfb.cdp.todo.dao;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;

import java.util.List;

public interface TodoListDao {
    List<Todo> findAll();

    Todo findById(Long id);

    Long save(Todo todo);

    void remove(Long id);

    void removeAll();

    void updateTodoStatusById(Long id, TodoStatus todoStatus);
}