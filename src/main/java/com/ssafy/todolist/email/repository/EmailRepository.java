package com.ssafy.todolist.email.repository;

import com.ssafy.todolist.email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

    Email findByEmail(String email);

}
