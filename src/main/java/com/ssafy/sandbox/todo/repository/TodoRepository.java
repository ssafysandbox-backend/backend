package com.ssafy.sandbox.todo.repository;

import com.ssafy.sandbox.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todos LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Todo> findAllByOffset(@Param("offset") int offset, @Param("limit") int limit);

    @Query("SELECT T FROM Todo T WHERE T.id >= :cursorId ORDER BY T.id LIMIT :size")
    List<Todo> findAllByCursorId(@Param("size") int size, @Param("cursorId") int cursorId);

    boolean existsByIdAfter(long id);
}
