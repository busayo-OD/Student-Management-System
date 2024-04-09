package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
