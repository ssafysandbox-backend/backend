package com.ssafy.sandbox.service;

import com.ssafy.sandbox.model.domain.Todo;
import com.ssafy.sandbox.model.domain.TodoVO;
import com.ssafy.sandbox.model.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
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

    @Transactional
    public void updateTodo(long todoId) throws Exception {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(Exception::new);
        todo.setCompleted(!todo.getCompleted());
    }
}
