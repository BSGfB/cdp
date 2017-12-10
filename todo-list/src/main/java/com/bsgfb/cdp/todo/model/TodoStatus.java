package com.bsgfb.cdp.todo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum TodoStatus {
    DONE,
    IN_PROGRESS;

    @JsonCreator
    public static TodoStatus createValue(final String value) {
        return Arrays.stream(TodoStatus.values()).filter(todoStatus -> value.equalsIgnoreCase(todoStatus.toString())).findFirst().orElse(IN_PROGRESS);
    }
}