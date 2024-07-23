package com.school.studentmanagementsystem.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    private String usernameOrEmail;
    private String password;
}
