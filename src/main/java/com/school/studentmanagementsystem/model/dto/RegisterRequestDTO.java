package com.school.studentmanagementsystem.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
