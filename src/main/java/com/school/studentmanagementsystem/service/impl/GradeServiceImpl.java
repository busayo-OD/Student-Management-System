package com.school.studentmanagementsystem.service.impl;

import com.school.studentmanagementsystem.exception.CourseNotFoundException;
import com.school.studentmanagementsystem.exception.GradeNotFoundException;
import com.school.studentmanagementsystem.exception.StudentNotFoundException;
import com.school.studentmanagementsystem.model.domain.Course;
import com.school.studentmanagementsystem.model.domain.Grade;
import com.school.studentmanagementsystem.model.domain.Student;
import com.school.studentmanagementsystem.model.dto.AddGradeDTO;
import com.school.studentmanagementsystem.model.dto.EditGradeDTO;
import com.school.studentmanagementsystem.model.dto.GradeDTO;
import com.school.studentmanagementsystem.model.enums.AssessmentType;
import com.school.studentmanagementsystem.repository.CourseRepository;
import com.school.studentmanagementsystem.repository.GradeRepository;
import com.school.studentmanagementsystem.repository.StudentRepository;
import com.school.studentmanagementsystem.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(
            GradeRepository gradeRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public GradeDTO addGrade(AddGradeDTO addGradeDTO) {
        String studentId = addGradeDTO.getStudentId();
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Long courseId = addGradeDTO.getCourseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setScore(addGradeDTO.getScore());
        grade.setAssessmentType(AssessmentType.valueOf(addGradeDTO.getAssessmentType()));
        grade.setRemarks(addGradeDTO.getRemarks());
        gradeRepository.save(grade);
        return mapToGradeDTO(grade);
    }

    @Override
    public GradeDTO viewStudentGrade(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Grade studentGrade = gradeRepository.findByStudent(student)
                .orElseThrow(() -> new GradeNotFoundException(student));
        return mapToGradeDTO(studentGrade);
    }

    @Override
    public List<GradeDTO> viewAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream().map(this::mapToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO editGrade(String studentId, EditGradeDTO gradeDTO) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Grade grade = gradeRepository.findByStudent(student)
                .orElseThrow(() -> new GradeNotFoundException(student));
        if(!gradeDTO.getAssessmentType().isEmpty()) {
            grade.setAssessmentType(AssessmentType.valueOf(gradeDTO.getAssessmentType()));
        }
        if(!gradeDTO.getScore().isNaN()){
            grade.setScore(gradeDTO.getScore());
        }
        if(!gradeDTO.getRemarks().isEmpty()){
            grade.setRemarks(gradeDTO.getRemarks());
        }
        gradeRepository.save(grade);
        return mapToGradeDTO(grade);
    }

    @Override
    public boolean deleteGrade(Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new GradeNotFoundException(gradeId));
        gradeRepository.delete(grade);
        return true;
    }

    private GradeDTO mapToGradeDTO (Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setStudentId(grade.getStudent().getStudentId());
        gradeDTO.setFirstname(grade.getStudent().getFirstname());
        gradeDTO.setLastname(grade.getStudent().getLastname());
        gradeDTO.setCourseCode(grade.getCourse().getCourseCode());
        gradeDTO.setCourseName(grade.getCourse().getCourseName());
        gradeDTO.setScore(grade.getScore());
        gradeDTO.setAssessmentType(String.valueOf(grade.getAssessmentType()));
        gradeDTO.setRemarks(grade.getRemarks());
        return gradeDTO;
    }
}
