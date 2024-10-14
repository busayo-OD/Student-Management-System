package com.school.studentmanagementsystem.service;

import com.school.studentmanagementsystem.model.dto.AddGradeDTO;
import com.school.studentmanagementsystem.model.dto.EditGradeDTO;
import com.school.studentmanagementsystem.model.dto.GradeDTO;

import java.util.List;

public interface GradeService {
    GradeDTO addGrade (AddGradeDTO addGradeDTO);
    GradeDTO viewStudentGrade (String studentId);
    List<GradeDTO> viewAllGrades();
    GradeDTO editGrade (String studentId, EditGradeDTO gradeDTO);
    boolean deleteGrade (Long gradeId);
}
