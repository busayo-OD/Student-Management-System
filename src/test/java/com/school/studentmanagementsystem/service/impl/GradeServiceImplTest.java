package com.school.studentmanagementsystem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

class GradeServiceImplTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private GradeServiceImpl gradeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addGrade_success() {
        Student student = new Student("S001", "John", "Doe");
        Course course = new Course(1L, "Computer Science", "CS101");
        AddGradeDTO addGradeDTO = new AddGradeDTO("S001", 1L, "Exam", 85.0 , "Good");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        GradeDTO gradeDTO = gradeServiceImpl.addGrade(addGradeDTO);

        assertEquals("S001", gradeDTO.getStudentId());
        assertEquals("CS101", gradeDTO.getCourseCode());
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void addGrade_studentNotFound() {
        AddGradeDTO addGradeDTO = new AddGradeDTO("S001", 1L, "Exam",85.0,  "Good");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> gradeServiceImpl.addGrade(addGradeDTO));
    }

    @Test
    void addGrade_courseNotFound() {
        Student student = new Student("S001", "John", "Doe");
        AddGradeDTO addGradeDTO = new AddGradeDTO("S001", 1L, "Exam",85.0,  "Good");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> gradeServiceImpl.addGrade(addGradeDTO));
    }

    @Test
    void viewStudentGrade_success() {
        Student student = new Student("S001", "John", "Doe");
        Grade grade = new Grade(student, new Course(1L, "CS101", "Computer Science"), AssessmentType.Exam,85.0,  "Good");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.of(student));
        when(gradeRepository.findByStudent(student)).thenReturn(Optional.of(grade));

        GradeDTO gradeDTO = gradeServiceImpl.viewStudentGrade("S001");

        assertEquals("S001", gradeDTO.getStudentId());
        assertEquals(85.0, gradeDTO.getScore());
    }

    @Test
    void viewStudentGrade_studentNotFound() {
        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> gradeServiceImpl.viewStudentGrade("S001"));
    }

    @Test
    void viewStudentGrade_gradeNotFound() {
        Student student = new Student("S001", "John", "Doe");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.of(student));
        when(gradeRepository.findByStudent(student)).thenReturn(Optional.empty());

        assertThrows(GradeNotFoundException.class, () -> gradeServiceImpl.viewStudentGrade("S001"));
    }

    @Test
    void viewAllGrades_success() {
        Grade grade1 = new Grade(new Student("S001", "John", "Doe"), new Course(1L, "CS101", "Computer Science"), AssessmentType.Exam, 85.0, "Good");
        Grade grade2 = new Grade(new Student("S002", "Jane", "Doe"), new Course(2L, "MTH101", "Mathematics"), AssessmentType.Assignment,90.0,  "Excellent");

        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade1, grade2));

        List<GradeDTO> grades = gradeServiceImpl.viewAllGrades();

        assertEquals(2, grades.size());
    }

    @Test
    void editGrade_success() {
        Student student = new Student("S001", "John", "Doe");
        Grade grade = new Grade(student, new Course(1L, "Computer Science", "CS101"), AssessmentType.Exam,85.0,  "Good");
        EditGradeDTO editGradeDTO = new EditGradeDTO("Assignment", 90.0, "Excellent");

        when(studentRepository.findByStudentId("S001")).thenReturn(Optional.of(student));
        when(gradeRepository.findByStudent(student)).thenReturn(Optional.of(grade));

        GradeDTO updatedGrade = gradeServiceImpl.editGrade("S001", editGradeDTO);

        assertEquals(90.0, updatedGrade.getScore());
        assertEquals("Assignment", updatedGrade.getAssessmentType());
    }

    @Test
    void deleteGrade_success() {
        Grade grade = new Grade();
        grade.setId(1L);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        boolean result = gradeServiceImpl.deleteGrade(1L);

        assertTrue(result);
        verify(gradeRepository, times(1)).delete(grade);
    }

    @Test
    void deleteGrade_gradeNotFound() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(GradeNotFoundException.class, () -> gradeServiceImpl.deleteGrade(1L));
    }
}
