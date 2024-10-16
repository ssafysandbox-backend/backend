package com.ssafy.todo.model.repository;

import com.ssafy.todo.model.domain.Todo;
import com.ssafy.todo.model.domain.TodoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<TodoVO> findAllProjectedByOrderByIdDesc();

    @Override
    Todo save(Todo todo);

    @Override
    void deleteById(Long todoId);

    @Override
    Optional<Todo> findById(Long todoId);
}
