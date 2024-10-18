package com.ssafy.todolist.domain;

import org.springframework.data.domain.Page;

import java.util.List;

public class OffsetResponse {

    private final String message;
    private final int currentPageNumber;
    private final int size;
    private final int totalPage;
    private final boolean hasNext;
    private final boolean hasPrevious;
    private final List<TodoDTO> todos;

    public static OffsetResponse build(String message, int currentPageNumber, int size, Page<Todo> page) {
        return new OffsetResponse(
                message,
                currentPageNumber,
                size,
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious(),
                page.stream().map(TodoDTO::of).toList()
        );
    }

    public OffsetResponse(String message, int currentPageNumber, int size, int totalPage, boolean hasNext, boolean hasPrevious, List<TodoDTO> todos) {
        this.message = message;
        this.currentPageNumber = currentPageNumber;
        this.size = size;
        this.totalPage = totalPage;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.todos = todos;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPage() {
        return totalPage;
    }
}