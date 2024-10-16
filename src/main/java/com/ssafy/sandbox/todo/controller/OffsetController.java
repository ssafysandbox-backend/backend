package com.ssafy.sandbox.todo.controller;

import com.ssafy.sandbox.todo.dto.CustomPage;
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
@RequestMapping("/offset")
@RequiredArgsConstructor
public class OffsetController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<?> getPageWithOffset(@RequestParam("size") int size, @RequestParam("page") int page) {
        List<TodoDTO> todoDTOList = todoService.getSomeTodosWithOffset(page, size);
        CustomPage customPage = CustomPage.builder()
                .message("정상적으로 요청되었습니다.")
                .currentPageNumber(page)
                .size(size)
                .hasNext(true)
                .todos(todoDTOList)
                .build();
        return ResponseEntity.ok().body(customPage);
    }
}
