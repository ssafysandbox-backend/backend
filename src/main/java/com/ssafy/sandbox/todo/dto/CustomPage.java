package com.ssafy.sandbox.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class CustomPage {

    private String message;
    private Integer currentPageNumber;
    private Integer size;
    private Boolean hasNext;
    private List<TodoDTO> todos;



    public CustomPage(String message, Integer currentPageNumber, Integer size, Boolean hasNext, List<TodoDTO> todos) {
        this.message = message;
        this.currentPageNumber = currentPageNumber;
        this.size = size;
        this.hasNext = hasNext;
        this.todos = todos;
    }
}
