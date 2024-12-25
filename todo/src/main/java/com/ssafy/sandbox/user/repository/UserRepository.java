package com.ssafy.sandbox.user.repository;

import com.ssafy.sandbox.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User save(User user);

    boolean existsById(Long id);
}
