package com.ssafy.sandbox.todo.service;

import com.ssafy.sandbox.todo.dto.PageTodoCursorResponse;
import com.ssafy.sandbox.todo.dto.PageTodoOffsetResponse;
import com.ssafy.sandbox.todo.dto.TodoDto;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageService {

    private final TodoRepository todoRepository;

    @Value("${success.message}")
    private String SUCCESS_MESSAGE;

    @Transactional
    public PageTodoOffsetResponse getTodosByOffset(int size, int page){
        int offset = (page - 1) * size;
        List<TodoDto> todosByOffset = todoRepository.findAllByOffset(offset, size)
                .stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());

        long totalTodos = todoRepository.count();
        int totalPage = (int) Math.ceil((double) totalTodos / size);
        boolean hasNext = (page+1)<totalPage;
        boolean hasPrevious = page>0;

        return PageTodoOffsetResponse.of(
                SUCCESS_MESSAGE,
                page,
                size,
                totalPage,
                hasNext,
                hasPrevious,
                todosByOffset
        );
    }

    public PageTodoCursorResponse getTodosByCursorId(int size, int cursorId) {
        List<TodoDto> todosByCursorId = todoRepository.findAllByCursorId(size, cursorId)
                .stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());

        Long lastId = 0L;
        if(!todosByCursorId.isEmpty()){
            lastId = todosByCursorId.get(todosByCursorId.size()-1).getId();
        }
        return PageTodoCursorResponse.of(
                SUCCESS_MESSAGE,
                lastId,
                todosByCursorId.size(),
                todoRepository.existsByIdAfter(lastId),
                todosByCursorId
        );
    }
}
