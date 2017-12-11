package com.bsgfb.cdp.todo.dao;

import com.bsgfb.cdp.todo.model.Todo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryTodoListDaoTest {

    private static final List<Todo> INIT_DATA_LIST = Arrays.asList(new Todo("Say hello"), new Todo("Write more tests"));

    @Test
    public void InMemoryTodoListConstructor() {
        assertTrue(new InMemoryTodoListDao().findAll().isEmpty());
    }

    @Test
    public void InMemoryTodoListConstructorWithArgs() {
        List<Todo> actualList = new InMemoryTodoListDao(INIT_DATA_LIST).findAll();

        assertEquals(INIT_DATA_LIST.size(), actualList.size());
    }


}