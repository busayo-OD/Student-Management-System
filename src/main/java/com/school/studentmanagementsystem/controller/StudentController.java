package com.school.studentmanagementsystem.controller;

import com.school.studentmanagementsystem.model.dto.*;
import com.school.studentmanagementsystem.service.StudentService;
import com.school.studentmanagementsystem.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent (@RequestBody RegisterStudentDTO registerStudentDTO){
        return ResponseEntity.ok(studentService.registerStudent(registerStudentDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> viewStudent(@PathVariable("studentId") String studentId){
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/my-profile")
    public ResponseEntity<StudentDTO> viewMyProfile(){
        String username = Objects.requireNonNull(CurrentUserUtil.getCurrentUser()).getUsername();
        return ResponseEntity.ok(studentService.getMyProfile(username));
    }
}
