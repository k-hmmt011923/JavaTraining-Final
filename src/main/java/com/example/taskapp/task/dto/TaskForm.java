package com.example.taskapp.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm {

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    private String title;

    // 編集画面で完了チェックも触るなら入れる（君の form.html に done があるので合わせる）
    private boolean done;

    public String getTitle() { return title; }
    public boolean isDone() { return done; }

    public void setTitle(String title) { this.title = title; }
    public void setDone(boolean done) { this.done = done; }
}