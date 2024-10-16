package com.ssafy.sandbox.todo.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TodoDTO {
    private long id;
    private String content;
    private boolean completed;

    public TodoDTO() {
    }

    public TodoDTO(long id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public static TodoDTO fromEntity(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getContent(), todo.isCompleted());
    }
}
