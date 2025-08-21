package com.food.auth_service.service;

import com.food.auth_service.dto.AuthRequest;
import com.food.auth_service.dto.AuthResponse;
import com.food.auth_service.model.Role;
import com.food.auth_service.model.UserCredential;
import com.food.auth_service.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public String saveUser(AuthRequest request, Role role) {
        UserCredential user = UserCredential.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.save(user);
        return "User registered successfully!";
    }

    public AuthResponse login(AuthRequest request) {
        UserCredential user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername());
            return new AuthResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
