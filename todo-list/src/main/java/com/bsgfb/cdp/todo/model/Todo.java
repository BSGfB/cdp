package com.bsgfb.cdp.todo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {
    private Long id;
    private String task;
    private TodoStatus status = TodoStatus.IN_PROGRESS;

    public Todo(final String task) {
        this.task = task;
    }

    public Todo(final String task, final TodoStatus status) {
        this.task = task;
        this.status = status;
    }

    @JsonCreator
    public Todo(@JsonProperty("id") final Long id, @JsonProperty("task") final String task, @JsonProperty("status") final TodoStatus status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(final String task) {
        this.task = task;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(final TodoStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        return (task != null ? task.equals(todo.task) : todo.task == null) && status == todo.status;
    }

    @Override
    public int hashCode() {
        int result = task != null ? task.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", status, task);
    }
}
