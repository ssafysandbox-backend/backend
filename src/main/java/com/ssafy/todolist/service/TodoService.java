package com.ssafy.todolist.service;

import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodoDTO;
import com.ssafy.todolist.repository.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getTodos() {
        return todoRepository.findAll().stream().map(TodoDTO::of).toList();
    }

    @Transactional
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
    }

    @Transactional
    public void updateTodo(int todoId) {
        Todo todo = todoRepository.getReferenceById(todoId);
        todo.setCompleted(!todo.getCompleted());
    }
}
