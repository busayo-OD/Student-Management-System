package com.school.studentmanagementsystem.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String studentId;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String address;
    private String email;
    private String phoneNumber;
    private String contactInformation;
    private String emergencyContact;
}
