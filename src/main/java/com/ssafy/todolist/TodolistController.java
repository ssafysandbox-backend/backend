package com.ssafy.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodolistController {

    private final TodoRepository todoRepository;

    public TodolistController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping()
    public TodolistDto todos() {
        TodolistDto todos = new TodolistDto();
        todos.setTodos(todoRepository.findAll());
        System.out.println(todos);
        return todos;
    }

    @PostMapping()
    public void addTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
        todos();
    }
}