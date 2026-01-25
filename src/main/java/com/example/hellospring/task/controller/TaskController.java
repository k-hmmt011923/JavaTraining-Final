package com.example.hellospring.task.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hellospring.task.entity.Task;
import com.example.hellospring.task.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 一覧取得（ソート条件付き）発展課題
    @GetMapping
    public List<Task> getTasks(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return taskService.findAll(sort, direction);
    }

    
    // 単体取得（存在しないIDならService側で例外→404）
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    // 作成（バリデーションエラーなら400）
    @PostMapping
    public Task addTask(@Valid @RequestBody Task task) {
        return taskService.add(task);
    }

    // 更新（バリデーションエラーなら400、存在しないIDなら404）
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    // 削除（存在しないIDなら404）
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}
