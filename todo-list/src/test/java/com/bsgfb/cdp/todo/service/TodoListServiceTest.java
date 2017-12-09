package com.bsgfb.cdp.todo.service;

import com.bsgfb.cdp.todo.dao.TodoListDao;
import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.easymock.EasyMock.*;

public class TodoListServiceTest {
    private static final long ID = 1L;
    private static final Todo TEST_TODO = new Todo(ID, "Create mock tests", TodoStatus.IN_PROGRESS);

    private TodoListService todoListService;
    private TodoListDao todoListDaoMock;

    @Before
    public void setUp() {
        todoListDaoMock = EasyMock.createNiceMock(TodoListDao.class);
        todoListService = new TodoServiceImpl(todoListDaoMock);
    }

    @After
    public void cleanUp() {
        verify(todoListDaoMock);
    }

    @Test
    public void findAll() {
        expect(todoListDaoMock.findAll()).andReturn(Collections.singletonList(new Todo("Create mock tests")));

        replay(todoListDaoMock);

        Assert.assertEquals(ID, todoListService.findAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNullTodo() {
        replay(todoListDaoMock);

        todoListService.save(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveEmptyTodo() {
        replay(todoListDaoMock);

        todoListService.save(new Todo(""));
    }

    @Test
    public void save() {
        expect(todoListDaoMock.save(TEST_TODO)).andReturn(TEST_TODO.getId());

        replay(todoListDaoMock);

        Assert.assertEquals(TEST_TODO.getId(), todoListService.save(TEST_TODO));
    }


    @Test
    public void remove() {
        todoListDaoMock.remove(ID);
        expectLastCall().times(1);

        replay(todoListDaoMock);

        todoListService.remove(ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNullId() {
        replay(todoListDaoMock);

        todoListService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNegativeId() {
        replay(todoListDaoMock);

        todoListService.remove(-ID);
    }


    @Test
    public void removeAll() {
        todoListDaoMock.removeAll();
        expectLastCall().times(1);

        replay(todoListDaoMock);

        todoListService.removeAll();
    }

    @Test
    public void findById() {
        expect(todoListDaoMock.findById(ID)).andReturn(TEST_TODO);

        replay(todoListDaoMock);

        Assert.assertEquals(TEST_TODO, todoListService.findById(TEST_TODO.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNullId() {
        replay(todoListDaoMock);

        todoListService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNegativeId() {
        replay(todoListDaoMock);

        todoListService.findById(-ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTodoStatusByNegativeId() {
        replay(todoListDaoMock);

        todoListService.updateTodoStatusById(-ID, TodoStatus.DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTodoStatusByIdWithNullStatus() {
        replay(todoListDaoMock);

        todoListService.updateTodoStatusById(ID, null);
    }

    @Test
    public void updateTodoStatusById() {
        todoListDaoMock.updateTodoStatusById(ID, TodoStatus.DONE);
        expectLastCall().times(1);

        replay(todoListDaoMock);

        todoListService.updateTodoStatusById(ID, TodoStatus.DONE);
    }
}