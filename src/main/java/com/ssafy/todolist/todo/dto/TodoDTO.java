package com.ssafy.todolist.todo.dto;

import com.ssafy.todolist.todo.domain.Todo;

public record TodoDTO(int id, String content, boolean completed) {

    public static TodoDTO of(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getContent(), todo.getCompleted());
    }
}
