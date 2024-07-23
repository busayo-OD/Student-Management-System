package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByTitle(String title);
}
