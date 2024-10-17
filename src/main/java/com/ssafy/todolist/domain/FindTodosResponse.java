package com.ssafy.todolist.domain;

import java.util.List;

public record FindTodosResponse(String message, List<TodoDTO> todos) {
}
