package com.ssafy.sandbox.todo.service;


import com.ssafy.sandbox.todo.dto.Todo;
import com.ssafy.sandbox.todo.dto.TodoDTO;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoDTO createTodo(String content) {
        Todo todo = Todo.from(content);
        return TodoDTO.fromEntity(todoRepository.save(todo));
    }

    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todoList = todoRepository.findAll().stream().map(TodoDTO::fromEntity).toList();
        return todoList;
    }

    public List<TodoDTO> getSomeTodosWithOffset(int offset, int size) {
        List<TodoDTO> todoList = todoRepository.findTodosWithOffset(offset * size, size).stream().map(TodoDTO::fromEntity).toList();
        return todoList;
    }

    public List<TodoDTO> getSomeTodosWithCursor(Long cursorId, int size) {
        List<TodoDTO> todoList = null;
        if (todoRepository.findMaxId() > cursorId) todoList = todoRepository.findTodosWithCursor(cursorId, size).stream().map(TodoDTO::fromEntity).toList();
        return todoList;
    }

    public boolean deleteTodo(Long id) {
        todoRepository.deleteById(id);
        return true;
    }

    public boolean changeState(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
        return false;
    }
}
