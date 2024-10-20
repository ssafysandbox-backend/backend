package com.ssafy.sandbox.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PageTodoCursorResponse {
    private String message;
    private long lastId;
    private int size;
    private boolean hasNext;
    private List<TodoDto> todos;

    @Builder
    public PageTodoCursorResponse(String message, long lastId, int size, boolean hasNext, List<TodoDto> todos) {
        this.message = message;
        this.lastId = lastId;
        this.size = size;
        this.hasNext = hasNext;
        this.todos = todos;
    }

    public static PageTodoCursorResponse of(String message, long lastId, int size, boolean hasNext, List<TodoDto> todos){
        return PageTodoCursorResponse.builder()
                .message(message)
                .lastId(lastId)
                .size(size)
                .hasNext(hasNext)
                .todos(todos)
                .build();
    }
}
