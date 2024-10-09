package com.ssafy.backend.controller;

import com.ssafy.backend.dto.TodosDto;
import com.ssafy.backend.dto.request.TodoRequest;
import com.ssafy.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping()
    public ResponseEntity<TodosDto> getTodos(){
        return ResponseEntity.ok().body(todoService.getTodos());
    }

    @PostMapping()
    public ResponseEntity<Void> addTodo(@RequestBody TodoRequest request){
        todoService.addTodo(request);
        return ResponseEntity.ok().build();
    }
}