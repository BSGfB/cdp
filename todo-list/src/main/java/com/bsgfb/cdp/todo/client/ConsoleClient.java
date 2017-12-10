package com.bsgfb.cdp.todo.client;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.model.TodoStatus;
import com.bsgfb.cdp.todo.service.TodoListService;
import com.bsgfb.cdp.todo.util.ConsoleInput;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleClient {
    private TodoListService todoListService;
    private ConsoleInput consoleInput;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ConsoleClient(final TodoListService todoListService, ConsoleInput consoleInput) {
        this.todoListService = todoListService;
        this.consoleInput = consoleInput;

        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    private Map<Long, Long> showTodoList() {
        System.out.println("Todo list:");
        List<Todo> all = todoListService.findAll().stream().sorted(Comparator.comparing(Todo::getStatus)).collect(Collectors.toList());

        Map<Long, Long> menuNumberTodoIdMap = new HashMap<>();
        for (int i = 0; i < all.size(); i++) {
            Todo currentTodo = all.get(i);

            menuNumberTodoIdMap.put((long) i, currentTodo.getId());
            System.out.println(String.format("[%2d] - %s", i, currentTodo));
        }

        return menuNumberTodoIdMap;
    }

    private void showSplitter() {
        System.out.println("\n=============================\n");
    }

    private void showControlMenu() {
        System.out.println("Menu:");
        Arrays.stream(MenuPoint.values()).forEach(System.out::println);
    }

    private void getOneController() {
        Map<Long, Long> ids = showTodoList();
        showSplitter();

        System.out.print("Witch one: ");
        long i = consoleInput.readNumber();

        if (isValidNumberInput(i, ids.size())) {
            Todo byId = todoListService.findById(ids.get(i));
            System.out.println("You selected:" + byId);
            changeStatusController(byId);
        } else {
            System.out.println("Wrong input");
        }
    }

    private void changeStatusController(Todo todo) {
        System.out.println("Do you want change todo status?(yes/no)");
        if(consoleInput.readString().equalsIgnoreCase("yes"))
            todoListService.updateTodoStatusById(todo.getId(), todo.getStatus() == TodoStatus.IN_PROGRESS ? TodoStatus.DONE: TodoStatus.IN_PROGRESS);
    }

    private void addTodoController() {
        System.out.println("Create new todo item");
        System.out.print("Task is: ");
        String task = consoleInput.readString();
        System.out.println("Task: " + task);
        if (isValidTextInput(task)) {
            todoListService.save(new Todo(task));
        } else {
            System.out.println("KEK");
        }
    }

    private void removeAllController() {
        System.out.println("Are you sure?(yes/no)");
        String answer = consoleInput.readString();

        if (isValidTextInput(answer) && answer.equalsIgnoreCase("yes")) {
            todoListService.removeAll();
            System.out.println("All records were deleted!");
        } else {
            System.out.println("You said no!");
        }
    }

    private void loadFromFileController() {
        System.out.println("Loading data from file");
        System.out.print("File address: ");
        String filePath = consoleInput.readString();
        try {
            Arrays.asList(objectMapper
                    .readValue(new FileInputStream(filePath), Todo[].class))
                    .forEach(todoListService::save);
        } catch (IOException e) {
            System.out.println("Cannot load from file: [" + e.getMessage() + "]");
        }
    }

    private void saveToFileController() {
        System.out.println("Saving data to file");
        System.out.print("File address: ");
        String filePath = consoleInput.readString();
        try {
            objectMapper.writeValue(new FileOutputStream(filePath), todoListService.findAll());
        } catch (IOException e) {
            System.out.println("Cannot write to file. Error: [" + e.getMessage() + "]");
        }
    }

    private void removeOneTodo() {
        Map<Long, Long> ids = showTodoList();
        showSplitter();

        System.out.print("Witch one: ");
        long i = consoleInput.readNumber();

        if (isValidNumberInput(i, ids.size())) {
            todoListService.remove(ids.get(i));
        } else {
            System.out.println("Wrong input");
        }
    }

    private void showAll() {
        showTodoList();
        System.out.print("[Press enter]");
        consoleInput.readString();
    }

    private void controller(MenuPoint menuPoint) {
        switch (menuPoint) {
            case EXIT:
                System.out.println("Application is going to exit!");
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
                System.out.println("Wrong input");
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
