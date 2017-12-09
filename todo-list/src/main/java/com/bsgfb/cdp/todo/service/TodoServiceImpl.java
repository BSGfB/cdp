package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.dao.TodoListDao;
import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;

import java.util.List;

import static java.util.Objects.isNull;

public class TodoServiceImpl implements TodoListService {
    private final TodoListDao todoListDao;

    public TodoServiceImpl(final TodoListDao todoListDao) {
        if (isNull(todoListDao))
            throw new IllegalArgumentException("todoListDao is null");

        this.todoListDao = todoListDao;
    }

    @Override
    public List<Todo> findAll() {
        return todoListDao.findAll();
    }

    @Override
    public Long save(final Todo todo) {
        if (isNull(todo) || isNull(todo.getTask()) || todo.getTask().equals(""))
            throw new IllegalArgumentException("Todo null or todo task is empty");

        return todoListDao.save(todo);
    }

    @Override
    public void remove(final Long id) {
        validateId(id);

        todoListDao.remove(id);
    }

    @Override
    public void removeAll() {
        todoListDao.removeAll();
    }

    @Override
    public Todo findById(Long id) {
        validateId(id);

        return todoListDao.findById(id);
    }

    @Override
    public void updateTodoStatusById(final Long id, final TodoStatus todoStatus) {
        validateId(id);
        if (isNull(todoStatus)) throw new IllegalArgumentException("todoStatus is null");

        todoListDao.updateTodoStatusById(id, todoStatus);
    }

    private void validateId(final Long id) {
        if (isNull(id) || id < 0)
            throw new IllegalArgumentException("id is null or above zero");
    }
}
