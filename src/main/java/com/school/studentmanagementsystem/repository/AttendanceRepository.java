package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
