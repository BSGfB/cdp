package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.dao.TodoListDao;
import com.bsgfb.cdp.todo.model.Todo;

import java.util.List;

public class TodoServiceImpl implements TodoListService {
    private final TodoListDao todoListDao;

    public TodoServiceImpl(final TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @Override
    public List<Todo> findAll() {
        return todoListDao.findAll();
    }

    @Override
    public Long save(final Todo todo) {
        return todoListDao.save(todo);
    }

    @Override
    public void remove(final Long id) {
        todoListDao.remove(id);
    }

    @Override
    public void removeAll() {
        todoListDao.removeAll();
    }

    @Override
    public Todo findById(Long id) {
        return todoListDao.findById(id);
    }
}
