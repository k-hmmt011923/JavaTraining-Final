package com.example.hellospring.task.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {
    // フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // タイトル（必須、最大50文字）
    //　タイトル未入力時
    @NotBlank(message = "タイトルを入力してください")
    // タイトル50文字超過時
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    @Column(nullable = false, length = 50)
    private String title;
    // 完了タグ（必須、デフォルトfalse）
    @Column(nullable = false)
    private boolean completed = false;
    
    //発展課題: 作成日時（自動設定、更新不可）
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Task() {
        // JPA用のデフォルトコンストラクタ
    }

    public Task(String title) {
        this.title = title;
    }

    // getters / setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

