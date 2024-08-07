package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    Optional<User> findByUsername (String username);
}
