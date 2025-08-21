package com.food.auth_service.controller;

import com.food.auth_service.dto.AuthRequest;
import com.food.auth_service.dto.AuthResponse;
import com.food.auth_service.model.Role;
import com.food.auth_service.service.AuthService;
import com.food.auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request,
                                           @RequestParam Role role) {
        return ResponseEntity.ok(authService.saveUser(request, role));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String token) {
        return ResponseEntity.ok(jwtService.validateToken(token));
    }
}
