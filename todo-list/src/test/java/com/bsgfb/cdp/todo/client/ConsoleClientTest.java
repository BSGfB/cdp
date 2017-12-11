package com.bsgfb.cdp.todo.client;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import com.bsgfb.cdp.todo.service.TodoListService;
import com.bsgfb.cdp.todo.util.UserInput;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.easymock.EasyMock.*;

public class ConsoleClientTest {
    private UserInput userInput;
    private TodoListService todoService;
    ConsoleClient consoleClient;

    @Before
    public void setUp() {
        userInput = EasyMock.createNiceMock(UserInput.class);
        todoService = EasyMock.createNiceMock(TodoListService.class);
        consoleClient = new ConsoleClient(todoService, userInput);
    }

    @After
    public void cleanUp() {
        verify(userInput);
        verify(todoService);
    }

    @Test
    public void showAllFlow() {
        expect(userInput.readNumber()).andReturn(7);
        expect(userInput.readString()).andReturn("\n");
        expect(todoService.findAll()).andReturn(Collections.emptyList());

        replay(userInput, todoService);

        consoleClient.run();
    }

    @Test
    public void showAddFlow() {
        String expected = "Say hello";

        expect(userInput.readNumber()).andReturn(1);
        expect(userInput.readString()).andReturn(expected);
        expect(todoService.save(new Todo(expected))).andReturn(1L);

        replay(userInput, todoService);

        consoleClient.run();
    }

    @Test
    public void showRemoveAllSayYesFlow() {
        expect(userInput.readNumber()).andReturn(3);
        expect(userInput.readString()).andReturn("yes");
        todoService.removeAll();

        expectLastCall().times(1);

        replay(userInput, todoService);

        consoleClient.run();
    }

    @Test
    public void showRemoveAllSayNoFlow() {
        expect(userInput.readNumber()).andReturn(3);
        expect(userInput.readString()).andReturn("no");
        replay(userInput, todoService);

        consoleClient.run();
    }

    @Test
    public void exitFlow() {
        expect(userInput.readNumber()).andReturn(0);
        replay(userInput, todoService);

        consoleClient.run();
    }

    @Test
    public void showRemoveOneFlow() {
        expect(userInput.readNumber()).andReturn(2).andReturn(0);
        expect(todoService.findAll()).andReturn(Collections.singletonList(new Todo(0L, "Say hello", TodoStatus.IN_PROGRESS)));

        todoService.remove(0L);
        expectLastCall().times(1);

        replay(userInput, todoService);

        consoleClient.run();
    }
}