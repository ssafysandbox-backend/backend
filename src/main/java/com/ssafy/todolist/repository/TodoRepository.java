package com.ssafy.todolist.repository;

import com.ssafy.todolist.domain.Todo;
import com.ssafy.todolist.domain.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
