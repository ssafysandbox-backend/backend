package com.ssafy.sandbox.todo.repository;

import com.ssafy.sandbox.todo.domain.Todo;
import com.ssafy.sandbox.todo.dto.TodoVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<TodoVO> findAllProjectsBy();

    @Override
    Todo save(Todo todo);

    @Override
    void deleteById(Long todoId);

    @Override
    Optional<Todo> findById(Long todoId);

    @Query("SELECT t FROM Todo t WHERE t.id > ?2 ORDER BY t.id LIMIT ?1")
    List<TodoVO> findAllByCursorId(int size, Long cursorId);

    boolean existsByIdAfter(Long id);

    @Query(value = "SELECT * FROM todo LIMIT :offset, :limit", nativeQuery = true)
    List<TodoVO> findAllByOffset(@Param("offset") int offset, @Param("limit") int limit);

    @Query("SELECT t FROM Todo t ORDER BY t.id")
    List<TodoVO> findAllByOffsetV2(PageRequest pageRequest);
}
