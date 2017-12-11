package com.bsgfb.cdp.todo.client;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import com.bsgfb.cdp.todo.service.TodoListService;
import com.bsgfb.cdp.todo.util.FileHelper;
import com.bsgfb.cdp.todo.util.UserInput;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.*;

public class ConsoleClientTest {
    private UserInput userInput;
    private TodoListService todoService;
    private FileHelper fileHelper;
    ConsoleClient consoleClient;

    @Before
    public void setUp() {
        userInput = EasyMock.createNiceMock(UserInput.class);
        todoService = EasyMock.createNiceMock(TodoListService.class);
        fileHelper = EasyMock.createNiceMock(FileHelper.class);

        consoleClient = new ConsoleClient(todoService, userInput, fileHelper);
    }

    @After
    public void cleanUp() {
        replay(userInput, todoService, fileHelper);

        consoleClient.run();

        verify(userInput);
        verify(todoService);
        verify(fileHelper);
    }

    @Test
    public void showAllFlow() {
        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.SHOW_ALL.getId());
        expect(userInput.readString()).andReturn("\n");
        expect(todoService.findAll()).andReturn(Collections.emptyList());
    }

    @Test
    public void addFlow() {
        String expected = "Say hello";

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.ADD_TODO.getId());
        expect(userInput.readString()).andReturn(expected);
        expect(todoService.save(new Todo(expected))).andReturn(1L);
    }

    @Test
    public void removeAllSayYesFlow() {
        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.REMOVE_ALL.getId());
        expect(userInput.readString()).andReturn("yes");
        todoService.removeAll();

        expectLastCall().times(1);
    }

    @Test
    public void removeAllSayNoFlow() {
        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.REMOVE_ALL.getId());
        expect(userInput.readString()).andReturn("no");
    }

    @Test
    public void exitFlow() {
        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.EXIT.getId());
    }

    @Test
    public void removeOneFlow() {
        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.REMOVE_TODO.getId()).andReturn(0);
        expect(todoService.findAll()).andReturn(Collections.singletonList(new Todo(0L, "Say hello", TodoStatus.IN_PROGRESS)));

        todoService.remove(0L);
        expectLastCall().times(1);
    }

    @Test
    public void getOneWithNoUpdateFlow() {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.IN_PROGRESS);

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.GET_ONE.getId()).andReturn(0);
        expect(userInput.readString()).andReturn("no");

        expect(todoService.findAll()).andReturn(Collections.singletonList(expectedTodo));
        expect(todoService.findById(0L)).andReturn(expectedTodo);
    }

    @Test
    public void getOneWithUpdateFlow() {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.IN_PROGRESS);

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.GET_ONE.getId()).andReturn(0);
        expect(userInput.readString()).andReturn("yes");

        expect(todoService.findAll()).andReturn(Collections.singletonList(expectedTodo));
        expect(todoService.findById(0L)).andReturn(expectedTodo);
    }

    @Test
    public void getOneWithUpdateFromDoneToInProgressFlow() {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.DONE);

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.GET_ONE.getId()).andReturn(0);
        expect(userInput.readString()).andReturn("yes");

        expect(todoService.findAll()).andReturn(Collections.singletonList(expectedTodo));
        expect(todoService.findById(0L)).andReturn(expectedTodo);
    }

    @Test
    public void getOneWithSaveToFileFlow() throws IOException {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.DONE);
        List<Todo> list = Collections.singletonList(expectedTodo);

        String pathToSave = "test.json";

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.SAVE_TO_FILE.getId());
        expect(userInput.readString()).andReturn(pathToSave);
        expect(todoService.findAll()).andReturn(list);

        fileHelper.toFile(pathToSave,list);
        expectLastCall().times(1);
    }

    @Test
    public void getOneWithReadFromFileFlow() throws IOException {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.DONE);
        List<Todo> list = Collections.singletonList(expectedTodo);

        String pathToSave = "test.json";

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.LOAD_FROM_FILE.getId());
        expect(userInput.readString()).andReturn(pathToSave);

        expect(fileHelper.fromFile(pathToSave)).andReturn(list);
    }

    @Test
    public void getOneWithReadFromFileFlowException() throws IOException {
        String pathToSave = "test.json";

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.LOAD_FROM_FILE.getId());
        expect(userInput.readString()).andReturn(pathToSave);

        expect(fileHelper.fromFile(pathToSave)).andThrow(new IOException());
    }

    @Test
    public void getOneWithSaveToFileFlowException() throws IOException {
        Todo expectedTodo = new Todo(0L, "Say hello", TodoStatus.DONE);
        List<Todo> list = Collections.singletonList(expectedTodo);

        String pathToSave = "test.json";

        expect(userInput.readNumber()).andReturn(ConsoleClient.MenuPoint.SAVE_TO_FILE.getId());
        expect(userInput.readString()).andReturn(pathToSave);
        expect(todoService.findAll()).andReturn(list);

        fileHelper.toFile(pathToSave, list);

        expectLastCall().andThrow(new IOException());
    }
}