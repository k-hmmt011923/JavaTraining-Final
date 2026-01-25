package com.example.hellospring.task.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.example.hellospring.task.entity.Task;
import com.example.hellospring.task.exception.TaskNotFoundException;
import com.example.hellospring.task.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 一覧取得
    public List<Task> findAll(String sort, String direction) {
    Sort.Direction dir = direction.equalsIgnoreCase("desc")
            ? Sort.Direction.DESC
            : Sort.Direction.ASC;

    return taskRepository.findAll(Sort.by(dir, sort));
}


    // 単体取得
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 作成
    public Task add(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    // 更新
    public Task update(Long id, Task updated) {
        Task existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setCompleted(updated.isCompleted());
        return taskRepository.save(existing);
    }

    // 削除
    public void delete(Long id) {
        Task existing = findById(id);
        taskRepository.delete(existing);
    }
}
