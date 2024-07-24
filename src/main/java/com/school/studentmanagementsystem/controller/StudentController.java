package com.school.studentmanagementsystem.controller;

import com.school.studentmanagementsystem.model.dto.RegisterStudentDTO;
import com.school.studentmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public boolean registerStudent (@RequestBody RegisterStudentDTO registerStudentDTO){
        return studentService.registerStudent(registerStudentDTO);
    }
}
