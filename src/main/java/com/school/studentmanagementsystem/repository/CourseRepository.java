package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
