package com.ssafy.sandbox.todo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TodoListResponse {
    private List<TodoDto> todos;

    public TodoListResponse(List<TodoDto> todos){
        this.todos = todos;
    }

    public List<TodoDto> getTodos(){
        return todos;
    }
}
