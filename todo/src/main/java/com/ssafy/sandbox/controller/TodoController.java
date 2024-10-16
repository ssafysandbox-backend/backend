package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.model.domain.TodoDTO;
import com.ssafy.sandbox.model.domain.TodoVO;
import com.ssafy.sandbox.service.TodoService;
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

    @PostMapping()
    public ResponseEntity<?> addTodo(@RequestBody TodoDTO body) {
        String content = body.getContent();
        log.debug("POST todo {}", content);
        todoService.save(content);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoId) {
        log.debug("delete todo id {}", todoId);
        todoService.deleteById(todoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable long todoId) {
        log.debug("PATCH todo ID {}", todoId);
        try {
            todoService.updateTodo(todoId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.created(null).build();
    }
}
