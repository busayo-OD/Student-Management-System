package com.school.studentmanagementsystem.service.impl;

import com.school.studentmanagementsystem.model.domain.Role;
import com.school.studentmanagementsystem.model.domain.Student;
import com.school.studentmanagementsystem.model.domain.User;
import com.school.studentmanagementsystem.model.dto.RegisterStudentDTO;
import com.school.studentmanagementsystem.repository.RoleRepository;
import com.school.studentmanagementsystem.repository.StudentRepository;
import com.school.studentmanagementsystem.repository.UserRepository;
import com.school.studentmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;

    @Override
    public boolean registerStudent(RegisterStudentDTO studentDTO) {
        User user = new User();
        if(userRepository.existsByUsername(studentDTO.getFirstName())){
            String generatedUsername = generateUniqueUsername(studentDTO.getFirstName());
            user.setUsername(generatedUsername);
        }
        else {
            user.setUsername(studentDTO.getFirstName());
        }
        user.setEmail(studentDTO.getEmail());
        user.setPhoneNumber(studentDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(studentDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByTitle("ROLE_STUDENT").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setStudentId(generateStudentId());
        student.setAddress(studentDTO.getAddress());
        student.setEmergencyContact(studentDTO.getEmergencyContact());
        student.setContactInformation(studentDTO.getContactInformation());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        studentRepository.save(student);

        return true;
    }

    private String generateUniqueUsername(String firstName) {
        Random random = new Random();
        int randomNumber1 = random.nextInt(10);
        int randomNumber2 = random.nextInt(10);
        int randomNumber3 = random.nextInt(10);

        return firstName + randomNumber1 + randomNumber2 + randomNumber3;
    }

    private String generateStudentId() {
        Random rand = new Random();
        int number = rand.nextInt(900000) + 100000;
        return String.format("%06d", number);
    }
}
