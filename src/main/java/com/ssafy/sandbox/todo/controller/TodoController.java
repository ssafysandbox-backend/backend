package com.ssafy.sandbox.todo.controller;

import com.ssafy.sandbox.todo.domain.Todo;
import com.ssafy.sandbox.todo.dto.TodoDto;
import com.ssafy.sandbox.todo.dto.TodoListResponse;
import com.ssafy.sandbox.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping()
    public TodoListResponse listAllTodos(){
        List<TodoDto> todos = todoService.getTodos();
        return new TodoListResponse(todos);
    }

    @PostMapping()
    public TodoDto createTodo(@RequestBody TodoDto todoDto){
        return todoService.createTodo(todoDto);
    }
}
