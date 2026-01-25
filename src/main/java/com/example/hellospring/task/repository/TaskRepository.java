package com.example.hellospring.task.repository;

import com.example.hellospring.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// タスクリポジトリ
public interface TaskRepository extends JpaRepository<Task, Long> {
}
