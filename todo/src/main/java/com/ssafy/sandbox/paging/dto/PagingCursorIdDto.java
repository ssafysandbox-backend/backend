package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.todo.dto.TodoVO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PagingCursorIdDto {
    private String message;
    private Long lastId;
    private int size;
    private boolean hasNext;
    private List<TodoVO> todos;

    @Builder
    public PagingCursorIdDto(String message, Long lastId, int size, boolean hasNext, List<TodoVO> todos) {
        this.message = message;
        this.lastId = lastId;
        this.size = size;
        this.hasNext = hasNext;
        this.todos = todos;
    }
}
