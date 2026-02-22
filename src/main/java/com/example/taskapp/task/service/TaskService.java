package com.example.taskapp.task.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.taskapp.task.entity.Task;
import com.example.taskapp.task.exception.TaskNotFoundException;
import com.example.taskapp.task.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 一覧（発展：sort / direction を受け取って並び替え）
    public List<Task> findAll(String sort, String direction) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return taskRepository.findAll(Sort.by(dir, sort));
    }

    // 1件取得（なければ例外→404）
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 画面：新規作成
    public void create(String title) {
        taskRepository.save(new Task(title));
    }

    // 画面：更新
    public void update(Long id, String title, boolean done) {
        Task existing = findById(id);
        existing.setTitle(title);
        existing.setCompleted(done);
        taskRepository.save(existing);
    }

    // 削除
    public void delete(Long id) {
        Task existing = findById(id);
        taskRepository.delete(existing);
    }

    // 完了切替
    public void toggle(Long id) {
        Task existing = findById(id);
        existing.setCompleted(!existing.isCompleted());
        taskRepository.save(existing);
    }
}