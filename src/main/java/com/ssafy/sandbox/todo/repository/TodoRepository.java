package com.ssafy.sandbox.todo.repository;


import com.ssafy.sandbox.todo.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoDTO, Long> {

    @Override
    TodoDTO save(TodoDTO entity);

    @Override
    List<TodoDTO> findAll();

    @Override
    Optional<TodoDTO> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}

