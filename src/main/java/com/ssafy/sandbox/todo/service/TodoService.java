package com.ssafy.sandbox.todo.service;

import com.ssafy.sandbox.todo.domain.Todo;
import com.ssafy.sandbox.todo.dto.TodoDto;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoDto> getTodos(){
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(todo -> new TodoDto(todo.getId(), todo.getContent(), todo.isDone()))
                .collect(Collectors.toList());
    }
}
