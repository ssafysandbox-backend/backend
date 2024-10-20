package com.ssafy.todolist.controller;

import com.ssafy.todolist.domain.CursorResponse;
import com.ssafy.todolist.domain.OffsetResponse;
import com.ssafy.todolist.service.PagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/paging")
@RestController
public class PagingController {

    private final PagingService pagingService;

    public PagingController(PagingService pagingService) {
        this.pagingService = pagingService;
    }

    @GetMapping("/offset")
    public ResponseEntity<OffsetResponse> getOffsetPaging(@RequestParam("size") Integer size, @RequestParam("page") Integer page) {
//        OffsetResponse offsetResponse = pagingService.getOffsetPaging(size, page);
        OffsetResponse offsetResponse = pagingService.getOffsetPagingV2(size, page);
        return ResponseEntity.ok(offsetResponse);
    }

    @GetMapping("/cursor")
    public ResponseEntity<CursorResponse> getCursorPaging(@RequestParam("size") int size, @RequestParam("cursorId") int cursorId) {
        CursorResponse cursorResponse = pagingService.getCursorPaging(size, cursorId);
        return ResponseEntity.ok(cursorResponse);
    }

}
