package com.ssafy.sandbox.todo.controller;

import com.ssafy.sandbox.todo.dto.PageTodoOffsetResponse;
import com.ssafy.sandbox.todo.dto.TodoDto;
import com.ssafy.sandbox.todo.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paging")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/offset")
    public ResponseEntity<PageTodoOffsetResponse> getTodosByOffset(@RequestParam int size, @RequestParam int page){
        System.out.println("in");
        PageTodoOffsetResponse response = pageService.getTodosByOffset(size, page);

        return ResponseEntity.ok().body(response);
    }
    
}
