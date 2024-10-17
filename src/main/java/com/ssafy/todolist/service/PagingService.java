package com.ssafy.todolist.service;

import com.ssafy.todolist.domain.OffsetResponse;
import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodoDTO;
import com.ssafy.todolist.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return new OffsetResponse("정상적으로 요청되었습니다.", page, size, p.getTotalPages(), p.hasNext(), p.hasPrevious(),  p.stream().map(TodoDTO::of).toList());
    }

}
