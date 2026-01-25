package com.example.hellospring.task.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 例外ハンドラ
@RestControllerAdvice
public class GlobalExceptionHandler {

    // タスク未発見例外ハンドラ
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFound(TaskNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Task not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    // バリデーション例外ハンドラ
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        // フィールドごとのエラーメッセージ取得
        Map<String, String> details = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            details.put(fe.getField(), fe.getDefaultMessage());
        }
        // レスポンスボディ作成
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Validation failed");
        body.put("details", details);

        // 400 Bad Requestで返す
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
