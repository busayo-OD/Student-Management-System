package com.school.studentmanagementsystem.controller;

import com.school.studentmanagementsystem.model.dto.AuthenticationRequestDTO;
import com.school.studentmanagementsystem.model.dto.AuthenticationResponse;
import com.school.studentmanagementsystem.model.dto.RegisterRequestDTO;
import com.school.studentmanagementsystem.service.AuthenticationService;
import com.school.studentmanagementsystem.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequestDTO request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
