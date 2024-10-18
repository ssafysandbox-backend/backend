package com.ssafy.todolist.domain;

import java.util.List;

public class CursorResponse {
    private final String message;
    private final int lastId;
    private final int size;
    private final boolean hasNext;
    private final List<TodoDTO> todos;

    public CursorResponse(String message, int lastId, int size, boolean hasNext, List<TodoDTO> todos) {
        this.message = message;
        this.lastId = lastId;
        this.size = size;
        this.hasNext = hasNext;
        this.todos = todos;
    }

    public String getMessage() {
        return message;
    }

    public int getLastId() {
        return lastId;
    }

    public int getSize() {
        return size;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }
}
