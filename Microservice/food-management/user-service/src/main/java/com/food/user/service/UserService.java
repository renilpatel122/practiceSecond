package com.food.user.service;

import com.food.user.dto.UserDTO;
import com.food.user.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO dto);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
