package com.ssafy.todolist.todo.dto;

import java.util.List;

public record FindTodosResponse(String message, List<TodoDTO> todos) {
}
