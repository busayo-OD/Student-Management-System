package com.school.studentmanagementsystem.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudentDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private String dateOfBirth;
    private String address;
    private String contactInformation;
    private String emergencyContact;
}
