package com.school.studentmanagementsystem.controller;

import com.school.studentmanagementsystem.model.dto.AddGradeDTO;
import com.school.studentmanagementsystem.model.dto.EditGradeDTO;
import com.school.studentmanagementsystem.model.dto.GradeDTO;
import com.school.studentmanagementsystem.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/add")
    public ResponseEntity<GradeDTO> addGrade (@RequestBody AddGradeDTO gradeDTO) {
        GradeDTO grade = gradeService.addGrade(gradeDTO);
        return ResponseEntity.ok(grade);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<GradeDTO> viewGrade (@PathVariable String studentId) {
        GradeDTO grade = gradeService.viewStudentGrade(studentId);
        return ResponseEntity.ok(grade);
    }

    @GetMapping()
    public ResponseEntity<List<GradeDTO>> viewAllGrades () {
        List<GradeDTO> grades = gradeService.viewAllGrades();
        return ResponseEntity.ok(grades);
    }

    @PutMapping("/edit/{studentId}")
    public ResponseEntity<GradeDTO> editGrade(@PathVariable String studentId, @RequestBody EditGradeDTO gradeDTO) {
        GradeDTO grade = gradeService.editGrade(studentId, gradeDTO);
        return ResponseEntity.ok(grade);
    }

    @DeleteMapping("{id}")
    public boolean deleteGrade (@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return true;
    }
}
