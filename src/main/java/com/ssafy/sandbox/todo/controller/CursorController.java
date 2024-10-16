package com.ssafy.sandbox.todo.controller;


import com.ssafy.sandbox.todo.dto.CustomPage;
import com.ssafy.sandbox.todo.dto.CustomPageWithCursor;
import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.service.TodoService;
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
@RequestMapping("/cursor")
@RequiredArgsConstructor
public class CursorController {
    private final TodoService todoService;


    @GetMapping
    public ResponseEntity<?> getPageWithCursor(@RequestParam("size") int size, @RequestParam("cursorId") Long cursorId) {
        List<TodoDTO> todoDTOList = todoService.getSomeTodosWithCursor(cursorId, size);
        Long lastId = todoDTOList.get(todoDTOList.size() - 1).getId();
        CustomPageWithCursor customPage = CustomPageWithCursor.builder()
                .message("정상적으로 요청되었습니다.")
                .lastId(lastId)
                .size(size)
                .hasNext(true)
                .todos(todoDTOList)
                .build();

        return ResponseEntity.ok().body(customPage);
    }
}
