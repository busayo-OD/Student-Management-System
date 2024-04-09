package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
