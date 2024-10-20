package com.ssafy.todolist.service;

import com.ssafy.todolist.domain.CursorResponse;
import com.ssafy.todolist.domain.OffsetResponse;
import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodoDTO;
import com.ssafy.todolist.repository.TodoRepository;
import com.ssafy.todolist.util.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagingService {

    private final TodoRepository todoRepository;

    public PagingService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public OffsetResponse getOffsetPaging(int size, int page) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Todo> p = todoRepository.findAll(pageable);
        return OffsetResponse.build(ResponseMessage.RESPONSE_SUCCESS.getMessage(), page, size, p);
//        return new OffsetResponse(ResponseMessage.RESPONSE_SUCCESS.getMessage(), page, size, p.getTotalPages(), p.hasNext(), p.hasPrevious(),  p.stream().map(TodoDTO::of).toList());
    }

    @Transactional(readOnly = true)
    public OffsetResponse getOffsetPagingV2(int size, int page) {
        int offset = size * (page - 1);
        List<TodoDTO> todos = todoRepository.findTodosByOffset(size, offset).stream().map(TodoDTO::of).toList();
        long count = todoRepository.count();
        int totalPageCount = (int) Math.ceil((double) count / size);
        boolean hasNext = page < totalPageCount;
        boolean hasPrevious = page > 1;
        return new OffsetResponse(ResponseMessage.RESPONSE_SUCCESS.getMessage(), page, size, totalPageCount, hasNext, hasPrevious, todos);
    }

    @Transactional(readOnly = true)
    public CursorResponse getCursorPaging(int size, int cursorId) {
        List<TodoDTO> todos = todoRepository.findTodosByCursor(size, cursorId).stream().map(TodoDTO::of).toList();
        int lastId = todos.get(todos.size()-1).id();
        boolean hasNext = todoRepository.existsByIdAfter(lastId);
        return new CursorResponse(ResponseMessage.RESPONSE_SUCCESS.getMessage(), lastId, size, hasNext, todos);
    }

}
