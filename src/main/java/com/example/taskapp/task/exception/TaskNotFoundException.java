package com.example.taskapp.task.exception;

public class TaskNotFoundException extends RuntimeException {
    private final Long id;

    public TaskNotFoundException(Long id) {
        super("Task not found: id=" + id);
        this.id = id;
    }

    public Long getId() { return id; }
}