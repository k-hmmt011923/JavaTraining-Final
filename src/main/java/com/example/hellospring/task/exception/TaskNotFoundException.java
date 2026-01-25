package com.example.hellospring.task.exception;

// タスク未発見例外処理
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task not found: id=" + id);
    }
}
