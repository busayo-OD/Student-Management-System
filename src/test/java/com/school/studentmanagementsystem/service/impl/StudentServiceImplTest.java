package com.school.studentmanagementsystem.service.impl;

import com.school.studentmanagementsystem.exception.StudentNotFoundException;
import com.school.studentmanagementsystem.model.domain.Role;
import com.school.studentmanagementsystem.model.domain.Student;
import com.school.studentmanagementsystem.model.domain.User;
import com.school.studentmanagementsystem.model.dto.RegisterStudentDTO;
import com.school.studentmanagementsystem.model.dto.StudentDTO;
import com.school.studentmanagementsystem.repository.RoleRepository;
import com.school.studentmanagementsystem.repository.StudentRepository;
import com.school.studentmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testRegisterStudent_Success() {
        RegisterStudentDTO studentDTO = new RegisterStudentDTO();
        studentDTO.setFirstname("John");
        studentDTO.setLastname("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPhoneNumber("123456789");
        studentDTO.setPassword("password123");

        User user = new User();
        user.setUsername("John");

        Role role = new Role();
        role.setTitle("ROLE_STUDENT");

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByTitle("ROLE_STUDENT")).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        StudentDTO result = studentService.registerStudent(studentDTO);

        // Then
        assertNotNull(result);
        verify(userRepository, times(1)).existsByUsername("John");
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepository, times(1)).save(any(User.class));
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testRegisterStudent_NullDTO_ShouldThrowException() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.registerStudent(null);
        });
        assertEquals("Student data or password cannot be null", exception.getMessage());
    }

    @Test
    public void testRegisterStudent_UserAlreadyExists_ShouldGenerateUniqueUsername() {
        // Given
        RegisterStudentDTO studentDTO = new RegisterStudentDTO();
        studentDTO.setFirstname("John");
        studentDTO.setLastname("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPassword("password123");

        when(userRepository.existsByUsername("John")).thenReturn(true);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);  // unique username generated
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByTitle("ROLE_STUDENT")).thenReturn(Optional.of(new Role()));

        // When
        studentService.registerStudent(studentDTO);

        // Then
        verify(userRepository, times(1)).existsByUsername("John");
        verify(userRepository, times(1)).existsByUsername(anyString());  // checks for unique username
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterStudent_NoRoleFound_ShouldThrowException() {
        // Given
        RegisterStudentDTO studentDTO = new RegisterStudentDTO();
        studentDTO.setFirstname("John");
        studentDTO.setLastname("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPassword("password123");

        when(roleRepository.findByTitle("ROLE_STUDENT")).thenReturn(Optional.empty());

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            studentService.registerStudent(studentDTO);
        });

        assertEquals("Student role not found", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));  // ensure user is not saved if role fails
    }

    @Test
    public void testGetStudent_Success() {
        // Given
        String studentId = "123456";
        Student student = new Student();
        student.setStudentId(studentId);
        student.setFirstname("John");
        student.setLastname("Doe");

        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.of(student));

        // When
        StudentDTO result = studentService.getStudent(studentId);

        // Then
        assertNotNull(result);
        assertEquals(studentId, result.getStudentId());
        verify(studentRepository, times(1)).findByStudentId(studentId);
    }

    @Test
    public void testGetStudent_NotFound_ShouldThrowException() {
        // Given
        String studentId = "123456";
        when(studentRepository.findByStudentId(studentId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudent(studentId);
        });
        verify(studentRepository, times(1)).findByStudentId(studentId);
    }

    @Test
    public void testGetAllStudents() {
        // Given
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setFirstname("John");
        student1.setLastname("Doe");

        Student student2 = new Student();
        student2.setFirstname("Jane");
        student2.setLastname("Smith");

        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        // When
        List<StudentDTO> result = studentService.getAllStudents();

        // Then
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testRegisterStudent_ShouldThrowException_WhenNoRoleFound() {
        // Given
        RegisterStudentDTO studentDTO = new RegisterStudentDTO();
        studentDTO.setFirstname("John");
        studentDTO.setLastname("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPassword("password123");

        when(roleRepository.findByTitle("ROLE_STUDENT")).thenReturn(Optional.empty());

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            studentService.registerStudent(studentDTO);
        });

        assertEquals("Student role not found", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

}