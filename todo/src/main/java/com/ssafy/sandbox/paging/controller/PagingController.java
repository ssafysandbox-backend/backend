package com.ssafy.sandbox.paging.controller;

import com.ssafy.sandbox.paging.dto.PagingCursorIdDto;
import com.ssafy.sandbox.paging.dto.PagingOffsetDto;
import com.ssafy.sandbox.paging.service.PagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PagingController {

    private final PagingService pagingService;

    @GetMapping("/offset")
    public ResponseEntity<?> getTodosByOffset(@RequestParam int size, @RequestParam int page) {
        log.debug("Called getTodosByOffset");
        log.debug("size: {}, page:{}", size, page);
//        PagingOffsetDto result = pagingService.getTodosByOffset(size, page);
        PagingOffsetDto result = pagingService.getTodosByOffsetV2(size, page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/cursor")
    public ResponseEntity<PagingCursorIdDto> getTodosByCursor(@RequestParam int size, @RequestParam Long cursorId) {
        log.debug("Called getTodosByCursor");
        log.debug("cursorId: {}, size: {}", cursorId, size);
        PagingCursorIdDto result = pagingService.getTodosByCursorId(size, cursorId);
        return ResponseEntity.ok().body(result);
    }
}
