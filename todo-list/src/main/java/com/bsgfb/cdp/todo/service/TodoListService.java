package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;

import java.util.List;

public interface TodoListService {
    /**
     * Return all stored todos
     * @return todo list
     */
    List<Todo> findAll();

    /**
     * Save new todo
     * @param todo new record. Todo task should be not null or empty
     * @return new record id
     */
    Long save(Todo todo);

    /**
     * Remove specific record
     * @param id to remove find specific record
     */
    void remove(Long id);

    /**
     * Remove all records
     */
    void removeAll();

    /**
     * Find specific record by by
     * @param id to find record. Id must be not null or bellow 0
     * @return stored todo with specified id
     */
    Todo findById(Long id);

    /**
     * Update todo status
     * @param id to find record. Id must be not null or bellow 0
     * @param todoStatus new todo status
     */
    void updateTodoStatusById(Long id, TodoStatus todoStatus);
}