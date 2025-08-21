package com.food.user.service.impl;

import com.food.user.dto.UserDTO;
import com.food.user.entity.User;
import com.food.user.exception.UserNotFoundException;
import com.food.user.repository.UserRepository;
import com.food.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User createUser(UserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .build();
        return repository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
