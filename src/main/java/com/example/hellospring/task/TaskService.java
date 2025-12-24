package com.example.hellospring.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Task> findAll() {
        return tasks;
    }

    public Task add(String title) {
        Task task = new Task(idGenerator.getAndIncrement(), title);
        tasks.add(task);
        return task;
    }
}
