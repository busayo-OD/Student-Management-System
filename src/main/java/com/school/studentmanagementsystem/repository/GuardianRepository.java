package com.school.studentmanagementsystem.repository;

import com.school.studentmanagementsystem.model.domain.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
}
