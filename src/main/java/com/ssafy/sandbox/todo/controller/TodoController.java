package com.ssafy.sandbox.todo.controller;


import com.ssafy.sandbox.todo.dto.RequestDTO;
import com.ssafy.sandbox.todo.dto.ResponseDTO;
import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import com.ssafy.sandbox.todo.service.TodoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping()
    public ResponseDTO getTodoList() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTodos(todoService.getAllTodos());
        return responseDTO;
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