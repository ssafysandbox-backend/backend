package com.ssafy.todolist.domain;

public record TodoDTO(int id, String content, boolean completed) {

    public static TodoDTO of(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getContent(), todo.getCompleted());
    }
}
