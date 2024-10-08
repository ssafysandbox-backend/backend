package com.ssafy.sandbox.todo.service;


import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoDTO createTodo(String content) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setContent(content);
        return todoRepository.save(todoDTO);
    }

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll();
    }

    public boolean deleteTodo(Long id) {
        todoRepository.deleteById(id);
        return true;
    }

    public boolean changeState(Long id) {
        TodoDTO todoDTO = todoRepository.findById(id).orElseThrow();
        todoDTO.setCompleted(!todoDTO.isCompleted());
        todoRepository.save(todoDTO);
        return false;
    }
}
