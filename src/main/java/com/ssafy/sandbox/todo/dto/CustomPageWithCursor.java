package com.ssafy.sandbox.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class CustomPageWithCursor {

    private String message;
    private Long lastId;
    private Integer size;
    private Boolean hasNext;
    private List<TodoDTO> todos;



    public CustomPageWithCursor(String message, Long lastId, Integer size, Boolean hasNext, List<TodoDTO> todos) {
        this.message = message;
        this.lastId = lastId;
        this.size = size;
        this.hasNext = hasNext;
        this.todos = todos;
    }
}
