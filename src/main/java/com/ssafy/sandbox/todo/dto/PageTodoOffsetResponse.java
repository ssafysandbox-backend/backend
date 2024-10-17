package com.ssafy.sandbox.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageTodoOffsetResponse {

    private String message;
    private int currentPageNumber;
    private int size;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<TodoDto> todos;

    public static PageTodoOffsetResponse of(String message, int currentPageNumber, int size, int totalPage, boolean hasNext, boolean hasPrevious, List<TodoDto> todos) {
        return PageTodoOffsetResponse.builder()
                .message(message)
                .currentPageNumber(currentPageNumber)
                .size(size)
                .totalPage(totalPage)
                .hasNext(hasNext)
                .hasPrevious(hasPrevious)
                .todos(todos)
                .build();
    }
}
