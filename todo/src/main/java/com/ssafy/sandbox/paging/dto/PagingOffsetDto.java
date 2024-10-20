package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.todo.dto.TodoVO;

import java.util.List;

public record PagingOffsetDto(String message, int currentPageNumber, int size, boolean hasNext,
                              List<TodoVO> todos) {
}
