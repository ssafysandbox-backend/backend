package com.ssafy.todolist.repository;

import com.ssafy.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

//    @Query(value = "select * from todos", nativeQuery = true)
//    Page<List<TodoDTO>> findAllByOffset(Pageable pageable);
}
