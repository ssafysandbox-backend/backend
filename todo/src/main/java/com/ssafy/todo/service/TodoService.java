package com.ssafy.todo.service;

import com.ssafy.todo.model.domain.Todo;
import com.ssafy.todo.model.domain.TodoVO;
import com.ssafy.todo.model.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoVO> findAll() {
        return todoRepository.findAllProjectedByOrderByIdDesc();
    }

    public void save(String content) {
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setCompleted(false);
        todoRepository.save(todo);
    }

    public void deleteById(long todoId) {
        todoRepository.deleteById(todoId);
    }
}
