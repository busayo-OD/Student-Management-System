package com.school.studentmanagementsystem.service;

import com.school.studentmanagementsystem.model.dto.RegisterStudentDTO;
import com.school.studentmanagementsystem.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO registerStudent (RegisterStudentDTO studentDTO);
    StudentDTO getStudent (String studentId);
    List<StudentDTO> getAllStudents();
    StudentDTO getMyProfile(String username);
}
