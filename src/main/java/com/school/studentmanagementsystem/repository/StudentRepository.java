package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Student;
import com.school.studentmanagementsystem.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId (String studentId);
    Optional<Student> findByUser (User user);
}
