package com.bsgfb.cdp.todo.client;

import com.bsgfb.cdp.todo.model.Todo;
import com.bsgfb.cdp.todo.service.TodoListService;

import java.util.*;

public class ConsoleClient {
    private TodoListService todoListService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleClient(final TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    private Map<Long, Long> showTodoList() {
        System.out.println("Todo list:");
        List<Todo> all = todoListService.findAll();

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
        long i = scannerNextNumber();

        if (isValidNumberInput(i, ids.size())) {
            System.out.println("You selected:" + todoListService.findById(ids.get(i)));
        }
        else {
            System.out.println("Wrong input");
        }
    }

    private void addTodoController() {
        System.out.println("Create new todo item");
        System.out.print("Task is: ");
        String task = scanner.nextLine();

        if (isValidTextInput(task)) {
            todoListService.save(new Todo(task));
        }
    }

    private void removeAllController() {
        System.out.println("Are you sure?(yes/no)");
        String answer = scanner.nextLine();

        if (isValidTextInput(answer) && answer.equalsIgnoreCase("yes")) {
            todoListService.removeAll();
            System.out.println("All records were deleted!");
        }
        else {
            System.out.println("You said no!");
        }
    }

    private void removeOneTodo() {
        Map<Long, Long> ids = showTodoList();
        showSplitter();

        System.out.print("Witch one: ");
        long i = scannerNextNumber();

        if (isValidNumberInput(i, ids.size())) {
            todoListService.remove(ids.get(i));
        }
        else {
            System.out.println("Wrong input");
        }
    }

    private void showAll() {
        showTodoList();
        System.out.print("[Press enter]");
        scanner.nextLine();
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
                break;
            case SAVE_TO_FILE:
                break;
            case SHOW_ALL:
                showAll();
                break;
            case UNDEFINED:
                System.out.println("Hahhahahaha");
                break;
        }
    }

    public void run() {
        MenuPoint menuPoint;

        do {
            showControlMenu();

            menuPoint = MenuPoint.getInstance(scannerNextNumber());
            controller(menuPoint);

        } while (menuPoint != MenuPoint.EXIT);
    }

    private int scannerNextNumber() {
        int i;
        try {
            i = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            i = Integer.MIN_VALUE;
        }
        finally {
            scanner.nextLine();
        }

        return i;
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
                case 0: return MenuPoint.EXIT;
                case 1: return MenuPoint.ADD_TODO;
                case 2: return MenuPoint.REMOVE_TODO;
                case 3: return MenuPoint.REMOVE_ALL;
                case 4: return MenuPoint.GET_ONE;
                case 5: return MenuPoint.SAVE_TO_FILE;
                case 6: return MenuPoint.LOAD_FROM_FILE;
                case 7: return MenuPoint.SHOW_ALL;
                default: return MenuPoint.UNDEFINED;
            }
        }

        @Override
        public String toString() {
            return String.format("[%2d] - %s", id, text);
        }
    }
}
