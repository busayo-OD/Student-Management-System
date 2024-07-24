package com.school.studentmanagementsystem.service;

import com.school.studentmanagementsystem.model.dto.RegisterStudentDTO;

public interface StudentService {
    boolean registerStudent (RegisterStudentDTO studentDTO);
}
