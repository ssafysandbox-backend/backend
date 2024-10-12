package com.ssafy.todolist.service;

import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodolistDto;
import com.ssafy.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodolistDto getTodos() {
        TodolistDto todos = new TodolistDto();
        todos.setTodos(todoRepository.findAll());
        return todos;
    }

    public void addTodo(Todo todo) {
        todoRepository.save(todo);
        getTodos();
    }

    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
        getTodos();
    }

    public void updateTodo(int todoId) {
        Todo todo = todoRepository.getReferenceById(todoId);
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
        getTodos();
    }
}
