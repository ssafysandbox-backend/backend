package com.ssafy.sandbox.todo.service;

import com.ssafy.sandbox.todo.domain.Todo;
import com.ssafy.sandbox.todo.dto.TodoDto;
import com.ssafy.sandbox.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .map(TodoDto::from)
                .collect(Collectors.toList());
    }

    public TodoDto createTodo(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setContent(todoDto.getContent());
//      기본값은 false
        todo.setCompleted(false);

        Todo savedTodo = todoRepository.save(todo);
        return TodoDto.from(savedTodo);
    }

    public void deleteTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo for delete not found(Illegal or inappropriate parameter)"));
        todoRepository.delete(todo);
    }

    public void updateTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo for update not found(Illegal or inappropriate parameter)"));

        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
    }


}