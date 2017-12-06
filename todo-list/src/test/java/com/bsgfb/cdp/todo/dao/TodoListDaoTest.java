package com.bsgfb.cdp.todo.dao;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class TodoListDaoTest {
    private TodoListDao todoListDao;

    @Before
    public void init() {
        todoListDao = new InMemoryTodoListDao();
    }

    @Test
    public void findAll() {
        List<Todo> expectedTodoList
                = asList(new Todo("test_1", TodoStatus.IN_PROGRESS), new Todo("test_2", TodoStatus.IN_PROGRESS));
        expectedTodoList.forEach(todoListDao::save);
        List<Todo> actualTodoList = todoListDao.findAll();

        assertNotNull(actualTodoList);
        assertEquals(expectedTodoList.size(), actualTodoList.size());

        expectedTodoList.forEach(expectedTodo -> {
            Todo actualTodo = actualTodoList.stream()
                    .filter(todo -> expectedTodo.getTask().equals(todo.getTask()))
                    .findFirst()
                    .orElse(null);

            assertNotNull(actualTodo);
            assertEquals(expectedTodo.getStatus(), actualTodo.getStatus());
        });
    }

    @Test
    public void save() {
        Todo expected = new Todo("test", TodoStatus.IN_PROGRESS);
        Long id = todoListDao.save(expected);
        Todo actual = todoListDao.findById(id);

        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(expected.getTask(), actual.getTask());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    public void remove() {
        int sizeBeforeRemoving = todoListDao.findAll().size();
        Long id = todoListDao.save(new Todo("test", TodoStatus.IN_PROGRESS));
        todoListDao.remove(id);
        int sizeAfterRemoving = todoListDao.findAll().size();

        assertEquals(sizeBeforeRemoving, sizeAfterRemoving);
    }

    @Test
    public void removeAll() {
        List<Todo> expectedTodoList
                = asList(new Todo("test_1", TodoStatus.IN_PROGRESS), new Todo("test_2", TodoStatus.IN_PROGRESS));
        expectedTodoList.forEach(todoListDao::save);

        todoListDao.removeAll();
        assertTrue(todoListDao.findAll().isEmpty());
    }

    @Test
    public void test() {
        asList(new Todo("test_1", TodoStatus.IN_PROGRESS), new Todo("test_2", TodoStatus.IN_PROGRESS))
        .forEach(System.out::println);

    }
}