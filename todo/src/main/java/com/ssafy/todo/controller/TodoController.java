package com.ssafy.todo.controller;

import com.ssafy.todo.model.domain.TodoDTO;
import com.ssafy.todo.model.domain.TodoVO;
import com.ssafy.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping()
    public ResponseEntity<?> getTodos() {
        List<TodoVO> todos = todoService.findAll();
        Map<String, List<TodoVO>> result = new HashMap<>();
        result.put("todos", todos);
        return ResponseEntity.ok().body(result);
    }
}
