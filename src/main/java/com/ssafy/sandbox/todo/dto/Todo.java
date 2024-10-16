package com.ssafy.sandbox.todo.dto;


import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;

    private boolean completed;

    public Todo() {
    }

    public Todo(String content) {
        this.content = content;
    }

    public Todo(long id, String content, boolean completed) {
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static Todo from (String content) {
        return new Todo(content);
    }
}
