package com.ssafy.sandbox.todo.controller;


import com.ssafy.sandbox.todo.dto.RequestDTO;
import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping()
    public ResponseEntity<?> getTodoList() {
        List<TodoDTO> todoList = todoService.getAllTodos();
        Map<String, List<TodoDTO>> result = new HashMap<>();
        result.put("todos", todoList);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public void addTodoList(@RequestBody RequestDTO data) {
        todoService.createTodo(data.getContent());
        return;
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return;
    }

    @PatchMapping("/{todoId}")
    public void changeState(@PathVariable Long todoId) {
        todoService.changeState(todoId);
        return;
    }
}