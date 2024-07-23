package com.school.studentmanagementsystem.service;

import com.school.studentmanagementsystem.model.dto.AuthenticationRequestDTO;
import com.school.studentmanagementsystem.model.dto.AuthenticationResponse;
import com.school.studentmanagementsystem.model.dto.RegisterRequestDTO;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequestDTO request);
    AuthenticationResponse login(AuthenticationRequestDTO request);
}
