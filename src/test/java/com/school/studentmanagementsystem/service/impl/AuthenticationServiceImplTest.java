package com.school.studentmanagementsystem.service.impl;

import com.school.studentmanagementsystem.exception.UsernameAlreadyExistsException;
import com.school.studentmanagementsystem.model.domain.User;
import com.school.studentmanagementsystem.model.dto.AuthenticationRequestDTO;
import com.school.studentmanagementsystem.model.dto.AuthenticationResponse;
import com.school.studentmanagementsystem.model.dto.RegisterRequestDTO;
import com.school.studentmanagementsystem.repository.UserRepository;
import com.school.studentmanagementsystem.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_UserAlreadyExists() {
        // Given
        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setUsername("existingUser");
        request.setEmail("user@example.com");

        // when
        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);

        // Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            authenticationService.register(request);
        });
    }

    @Test
    void testLogin_Success() {
        // Given
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        request.setUsernameOrEmail("user@example.com");
        request.setPassword("password");

        User user = new User();
        user.setUsername("user");
        user.setPassword("encodedPassword");

        // when
        when(userRepository.findByUsernameOrEmail(anyString(), anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtService.generateToken(user)).thenReturn("mockedToken");

        AuthenticationResponse response = authenticationService.login(request);

        // Assert
        assertNotNull(response.getToken());
        assertEquals("mockedToken", response.getToken());
        verify(jwtService, times(1)).generateToken(user);
    }

}
