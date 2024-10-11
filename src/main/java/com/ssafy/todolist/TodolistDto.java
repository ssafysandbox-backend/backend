package com.ssafy.todolist;

import java.util.ArrayList;
import java.util.List;

public class TodolistDto {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
