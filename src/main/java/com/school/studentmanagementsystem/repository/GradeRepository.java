package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Grade;
import com.school.studentmanagementsystem.model.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByStudent (Student student);
}
