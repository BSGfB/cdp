package com.bsgfb.cdp.todo.dao;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;

import java.util.List;

public interface TodoListDao {
    /**
     * @return list todo items
     */
    List<Todo> findAll();

    /**
     * Find and return item by specific id
     * @param id of todo item
     * @return todo item
     */
    Todo findById(Long id);

    /**
     *
     * @param todo item, id filed will be ignored
     * @return id, new added item
     */
    Long save(Todo todo);

    /**
     * Remove record by id
     * @param id
     */
    void remove(Long id);

    /**
     * Remove all todo items
     */
    void removeAll();

    /**
     * Update record status
     * All available status you can see in TodoStatus
     * @param id record
     * @param todoStatus status to set
     */
    void updateTodoStatusById(Long id, TodoStatus todoStatus);
}