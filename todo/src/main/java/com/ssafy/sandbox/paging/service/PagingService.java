package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.PagingCursorIdDto;
import com.ssafy.sandbox.todo.dto.TodoVO;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagingService {

    @Value("${HTTP.SUCCESS.MESSAGE}")
    private String SUCCESS_MESSAGE;
    private final TodoRepository todoRepository;

    public PagingCursorIdDto getTodosByCursorId(int size, Long cursorId) {
        List<TodoVO> result = todoRepository.findAllByCursorId(size, cursorId);
        long lastIndex = 0L;
        if (!result.isEmpty()) {
            lastIndex = result.get(result.size() - 1).getId();
        }

        return PagingCursorIdDto.builder()
                .message(SUCCESS_MESSAGE)
                .lastId(lastIndex)
                .size(result.size())
                .hasNext(todoRepository.existsByIdAfter(cursorId))
                .todos(result)
                .build();
    }
}
