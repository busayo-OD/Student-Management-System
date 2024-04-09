package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
