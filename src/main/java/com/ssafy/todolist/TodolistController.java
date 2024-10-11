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
        System.out.println("select");
        TodolistDto todos = new TodolistDto();
        todos.setTodos(todoRepository.findAll());
        System.out.println(todos);
        return todos;
    }

    @PostMapping()
    public void addTodo(@RequestBody Todo todo) {
        System.out.println("insert");
        todoRepository.save(todo);
        todos();
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") int todoId) {
        System.out.println("delete");
        todoRepository.deleteById(todoId);
        todos();
    }

    @PatchMapping("/{todoId}")
    public void updateTodo(@PathVariable("todoId") int todoId) {
        System.out.println("update");
        Todo todo = todoRepository.getReferenceById(todoId);
        if (todo.getCompleted()) {
            todo.setCompleted(false);
        } else if (!todo.getCompleted()) {
            todo.setCompleted(true);
        }
        todoRepository.save(todo);
        todos();
    }
}