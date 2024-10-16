package com.ssafy.sandbox.todo.repository;


import com.ssafy.sandbox.todo.dto.Todo;
import com.ssafy.sandbox.todo.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Override
    Todo save(Todo entity);

    @Override
    List<Todo> findAll();

    @Override
    Optional<Todo> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Query(value = "SELECT max(id) from todos", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT id, content, completed FROM todos ORDER BY id ASC LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Todo> findTodosWithOffset(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT id, content, completed FROM todos Where id > :cursorId ORDER BY id ASC LIMIT :limit",
            nativeQuery = true)
    List<Todo> findTodosWithCursor(@Param("cursorId") Long cursorId, @Param("limit") int limit);
}

