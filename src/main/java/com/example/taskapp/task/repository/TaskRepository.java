package com.example.taskapp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapp.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}