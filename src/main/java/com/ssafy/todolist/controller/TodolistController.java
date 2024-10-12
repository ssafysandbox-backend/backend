package com.ssafy.todolist.controller;

import com.ssafy.todolist.domain.TodolistDto;
import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.repository.TodoRepository;
import com.ssafy.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/todos")
@RestController
public class TodolistController {

    private final TodoService todoService;

    public TodolistController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public TodolistDto todos() {
        System.out.println("select");
        return todoService.getTodos();
    }

    @PostMapping()
    public void addTodo(@RequestBody Todo todo) {
        System.out.println("insert");
        todoService.addTodo(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") int todoId) {
        System.out.println("delete");
        todoService.deleteTodo(todoId);
    }

    @PatchMapping("/{todoId}")
    public void updateTodo(@PathVariable("todoId") int todoId) {
        System.out.println("update");
        todoService.updateTodo(todoId);
    }
}