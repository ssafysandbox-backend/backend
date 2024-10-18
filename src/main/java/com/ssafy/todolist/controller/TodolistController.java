package com.ssafy.todolist.controller;

import com.ssafy.todolist.domain.FindTodosResponse;
import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodoDTO;
import com.ssafy.todolist.service.TodoService;
import com.ssafy.todolist.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodolistController {

    private final TodoService todoService;

    public TodolistController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<FindTodosResponse> todos() {
        List<TodoDTO> todos = todoService.getTodos();
        FindTodosResponse response = new FindTodosResponse(ResponseMessage.RESPONSE_SUCCESS.getMessage(), todos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public void addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") int todoId) {
        todoService.deleteTodo(todoId);
    }

    @PatchMapping("/{todoId}")
    public void updateTodo(@PathVariable("todoId") int todoId) {
        todoService.updateTodo(todoId);
    }
}