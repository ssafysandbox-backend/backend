package com.ssafy;

import com.ssafy.todolist.domain.TodoDTO;

import java.util.List;

public record FindTodosResponse(String message, List<TodoDTO> todos) {
}
