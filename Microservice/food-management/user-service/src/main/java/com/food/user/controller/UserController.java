package com.food.user.controller;

import com.food.user.dto.UserDTO;
import com.food.user.entity.User;
import com.food.user.service.TokenValidator;
import com.food.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final TokenValidator tokenValidator;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestHeader("Authorization") String token,
                                           @RequestBody UserDTO dto) {
        validate(token);
        return ResponseEntity.ok(service.createUser(dto));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token,
                                        @PathVariable String username) {
        validate(token);
        return ResponseEntity.ok(service.getUserByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestHeader("Authorization") String token) {
        validate(token);
        return ResponseEntity.ok(service.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String token,
                                         @PathVariable Long id) {
        validate(token);
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    private void validate(String token) {
        String clean = token.replace("Bearer ", "");
        if (!tokenValidator.validateToken(clean)) {
            throw new RuntimeException("Invalid token");
        }
    }
}
