package com.bsgfb.cdp.todo.dao;

import com.bsgfb.cdp.todo.model.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTodoListDao implements TodoListDao {
    private Map<Long, Todo> tasks;
    private static AtomicLong keyCounter = new AtomicLong(0);

    public InMemoryTodoListDao() {
        tasks = new HashMap<>();
    }

    public InMemoryTodoListDao(List<Todo> initData) {
        tasks = initData.stream().map(this::setIdForTodo).collect(Collectors.toMap(Todo::getId, Function.identity()));
    }

    private Todo setIdForTodo(final Todo todo) {
        todo.setId(keyCounter.getAndIncrement());
        return todo;
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Todo findById(Long id) {
        return tasks.get(id);
    }

    @Override
    public Long save(final Todo todo) {
        Todo updatedTodo = setIdForTodo(todo);
        tasks.put(updatedTodo.getId(), updatedTodo);

        return updatedTodo.getId();
    }

    @Override
    public void remove(Long id) {
        tasks.remove(id);
    }

    @Override
    public void removeAll() {
        tasks.clear();
    }
}
