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
import com.school.studentmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;

    @Override
    public StudentDTO registerStudent(RegisterStudentDTO studentDTO) {
        if (studentDTO == null || studentDTO.getPassword() == null) {
            throw new IllegalArgumentException("Student data or password cannot be null");
        }

        User user = new User();
        String username = studentDTO.getFirstname();

        if (userRepository.existsByUsername(username)) {
            username = generateUniqueUsername(username);
        }
        user.setUsername(username);
        user.setFirstname(studentDTO.getFirstname());
        user.setLastname(studentDTO.getLastname());
        user.setEmail(studentDTO.getEmail());
        user.setPhoneNumber(studentDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(studentDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByTitle("ROLE_STUDENT");
        if (userRole.isPresent()) {
            roles.add(userRole.get());
        } else {
            throw new IllegalStateException("Student role not found");
        }
        user.setRoles(roles);
        userRepository.save(user);

        Student student = new Student();
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setEmail(studentDTO.getEmail());
        student.setStudentId(generateStudentId());
        student.setAddress(studentDTO.getAddress());
        student.setEmergencyContact(studentDTO.getEmergencyContact());
        student.setContactInformation(studentDTO.getContactInformation());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setUser(user);
        studentRepository.save(student);

        return mapToStudentDTO(student);
    }

    private String generateUniqueUsername(String firstName) {
        Random random = new Random();
        String uniqueUsername;
        do {
            uniqueUsername = firstName + random.nextInt(1000);
        } while (userRepository.existsByUsername(uniqueUsername));
        return uniqueUsername;
    }

    private String generateStudentId() {
        Random rand = new Random();
        int number = rand.nextInt(900000) + 100000;
        return String.format("%06d", number);
    }

    @Override
    public StudentDTO getStudent (String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        return mapToStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::mapToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getMyProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new StudentNotFoundException(user.getUsername()));
        return mapToStudentDTO(student);
    }



    private StudentDTO mapToStudentDTO (Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        return studentDTO;
    }
}
