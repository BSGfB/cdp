package com.bsgfb.cdp.todo.client;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import com.bsgfb.cdp.todo.service.TodoListService;
import com.bsgfb.cdp.todo.util.ConsoleInput;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleClient {
    private static final Logger LOGGER = LogManager.getLogger(ConsoleClient.class);

    private TodoListService todoListService;
    private ConsoleInput consoleInput;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ConsoleClient(final TodoListService todoListService, ConsoleInput consoleInput) {
        this.todoListService = todoListService;
        this.consoleInput = consoleInput;

        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    private Map<Long, Long> showTodoList() {
        LOGGER.debug("Todo list:");
        List<Todo> all = todoListService.findAll().stream().sorted(Comparator.comparing(Todo::getStatus)).collect(Collectors.toList());

        Map<Long, Long> menuNumberTodoIdMap = new HashMap<>();
        for (int i = 0; i < all.size(); i++) {
            Todo currentTodo = all.get(i);

            menuNumberTodoIdMap.put((long) i, currentTodo.getId());
            LOGGER.debug(String.format("[%2d] - %s", i, currentTodo));
        }

        return menuNumberTodoIdMap;
    }

    private void showSplitter() {
        LOGGER.debug("\n=============================\n");
    }

    private void showControlMenu() {
        LOGGER.debug("Menu:");
        Arrays.stream(MenuPoint.values()).forEach(LOGGER::debug);
    }

    private void getOneController() {
        Map<Long, Long> ids = showTodoList();
        showSplitter();

        LOGGER.debug("Witch one: ");
        long i = consoleInput.readNumber();

        if (isValidNumberInput(i, ids.size())) {
            Todo byId = todoListService.findById(ids.get(i));
            LOGGER.debug("You selected:" + byId);
            changeStatusController(byId);
        } else {
            LOGGER.debug("Wrong input");
        }
    }

    private void changeStatusController(Todo todo) {
        LOGGER.debug("Do you want change todo status?(yes/no)");
        if(consoleInput.readString().equalsIgnoreCase("yes"))
            todoListService.updateTodoStatusById(todo.getId(), todo.getStatus() == TodoStatus.IN_PROGRESS ? TodoStatus.DONE: TodoStatus.IN_PROGRESS);
    }

    private void addTodoController() {
        LOGGER.debug("Create new todo item");
        LOGGER.debug("Task is: ");
        String task = consoleInput.readString();
        LOGGER.debug("Task: " + task);
        if (isValidTextInput(task)) {
            todoListService.save(new Todo(task));
        } else {
            LOGGER.debug("KEK");
        }
    }

    private void removeAllController() {
        LOGGER.debug("Are you sure?(yes/no)");
        String answer = consoleInput.readString();

        if (isValidTextInput(answer) && answer.equalsIgnoreCase("yes")) {
            todoListService.removeAll();
            LOGGER.debug("All records were deleted!");
        } else {
            LOGGER.debug("You said no!");
        }
    }

    private void loadFromFileController() {
        LOGGER.debug("Loading data from file");
        LOGGER.debug("File address: ");
        String filePath = consoleInput.readString();
        try {
            Arrays.asList(objectMapper
                    .readValue(new FileInputStream(filePath), Todo[].class))
                    .forEach(todoListService::save);
        } catch (IOException e) {
            LOGGER.debug("Cannot load from file: [" + e.getMessage() + "]");
        }
    }

    private void saveToFileController() {
        LOGGER.debug("Saving data to file");
        LOGGER.debug("File address: ");
        String filePath = consoleInput.readString();
        try {
            objectMapper.writeValue(new FileOutputStream(filePath), todoListService.findAll());
        } catch (IOException e) {
            LOGGER.debug("Cannot write to file. Error: [" + e.getMessage() + "]");
        }
    }

    private void removeOneTodo() {
        Map<Long, Long> ids = showTodoList();
        showSplitter();

        LOGGER.debug("Witch one: ");
        long i = consoleInput.readNumber();

        if (isValidNumberInput(i, ids.size())) {
            todoListService.remove(ids.get(i));
        } else {
            LOGGER.debug("Wrong input");
        }
    }

    private void showAll() {
        showTodoList();
        LOGGER.debug("[Press enter]");
        consoleInput.readString();
    }

    private void controller(MenuPoint menuPoint) {
        switch (menuPoint) {
            case EXIT:
                LOGGER.debug("Application is going to exit!");
                break;
            case GET_ONE:
                getOneController();
                break;
            case ADD_TODO:
                addTodoController();
                break;
            case REMOVE_ALL:
                removeAllController();
                break;
            case REMOVE_TODO:
                removeOneTodo();
                break;
            case LOAD_FROM_FILE:
                loadFromFileController();
                break;
            case SAVE_TO_FILE:
                saveToFileController();
                break;
            case SHOW_ALL:
                showAll();
                break;
            case UNDEFINED:
                LOGGER.debug("Wrong input");
                break;
        }
    }

    public void run() {
        MenuPoint menuPoint;

        do {
            showControlMenu();

            menuPoint = MenuPoint.getInstance(consoleInput.readNumber());
            controller(menuPoint);

        } while (menuPoint != MenuPoint.EXIT);
    }

    private boolean isValidTextInput(String text) {
        return text != null && text.length() > 0;
    }

    private boolean isValidNumberInput(long number, int to) {
        return number >= 0 && number < to;
    }

    private enum MenuPoint {
        EXIT(0, "exit"),
        ADD_TODO(1, "Add todo"),
        REMOVE_TODO(2, "Remove one"),
        REMOVE_ALL(3, "Remove all"),
        GET_ONE(4, "Get one"),
        SAVE_TO_FILE(5, "Save to file"),
        LOAD_FROM_FILE(6, "Load from file"),
        SHOW_ALL(7, "Show all"),
        UNDEFINED(Integer.MAX_VALUE, "Undefined");

        private Integer id;
        private String text;

        MenuPoint(final Integer id, final String text) {
            this.id = id;
            this.text = text;
        }

        public static MenuPoint getInstance(Integer number) {
            switch (number) {
                case 0:
                    return MenuPoint.EXIT;
                case 1:
                    return MenuPoint.ADD_TODO;
                case 2:
                    return MenuPoint.REMOVE_TODO;
                case 3:
                    return MenuPoint.REMOVE_ALL;
                case 4:
                    return MenuPoint.GET_ONE;
                case 5:
                    return MenuPoint.SAVE_TO_FILE;
                case 6:
                    return MenuPoint.LOAD_FROM_FILE;
                case 7:
                    return MenuPoint.SHOW_ALL;
                default:
                    return MenuPoint.UNDEFINED;
            }
        }

        @Override
        public String toString() {
            return String.format("[%2d] - %s", id, text);
        }
    }
}
