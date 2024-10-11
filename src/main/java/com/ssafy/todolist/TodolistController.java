package com.ssafy.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodolistController {

    @GetMapping()
    public TodolistDto todos() {
        TodolistDto todos = new TodolistDto();
        System.out.println(todos);
        return todos;
    }

    @PostMapping()
    public TodolistDto addTodo() {

    }
}