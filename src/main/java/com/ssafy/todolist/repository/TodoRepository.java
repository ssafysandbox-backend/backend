package com.ssafy.todolist.repository;

import com.ssafy.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query(value = "select * from todos where id > :cursorId limit :size", nativeQuery = true)
    List<Todo> findTodosByCursor(@RequestParam(name = "size") int size, @RequestParam(name = "cursorId") int cursorId);

    boolean existsByIdAfter(int id);
}
